package Controller;

import Model.ADT.*;
import Model.PrgState;
import Model.ProgramException;
import Model.Statement.IStmt;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.RefValue;
import Repository.IRepository;
import com.sun.jdi.IntegerValue;
import com.sun.jdi.Value;

import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Controller {
    private IRepository repository;
    private boolean displayFlag;
    private ExecutorService executor;

    public Controller(IRepository target, boolean display) {
        repository = target;
        displayFlag = display;
    }

    private Map<Integer,IValue> unsafeGarbageCollector(List<Integer> symTableAddr,
                                                                     Map<Integer, IValue> heap)
    {
         //HashMap<Integer,IValue> map = new HashMap<Integer, IValue>();
         return heap.entrySet()
                .stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                 .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
                //.forEach(entry->map.put(entry.getKey(),entry.getValue()));
         //return map;
    }

    private void conservativeGarbageCollector(IList<PrgState> threads) {
        ProgramList<Integer> allAddresses = new ProgramList<Integer>();
        threads.stream()
                .forEach(thread->allAddresses.addAll(getAddrFromSymTable(thread.getSymTable()
                        .getContent()
                        .values())));
        threads.current(0).getHeap().setContent(unsafeGarbageCollector(
                allAddresses.stream()
                        .collect(Collectors.toList()),
                threads.current(0)
                        .getHeap()
                        .getContent()));
    }

    private List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

/*    private PrgState oneStep(PrgState state) throws ProgramException {
        IStack<IStmt> stk= state.getStk();
        if(stk.isEmpty())
            throw new ProgramException("Prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }*/

    private void oneStepForAllPrg(IList<PrgState> prgList) {
        prgList.stream().forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (ProgramException e) {
                e.printStackTrace();
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState prg) -> (Callable<PrgState>)(prg::oneStep))
                .collect(Collectors.toList());
        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    . map(future -> { try { return future.get();}
                        catch(Exception exception) {
                            System.out.println(exception.toString());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            prgList.addAll(newPrgList);
            if(displayFlag)
                prgList.stream().forEach(prg -> {
                    try {
                        repository.logPrgStateExec(prg);
                    } catch (ProgramException e) {
                        e.printStackTrace();
                    }
                });
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        repository.setPrgList(new ProgramList<PrgState>(prgList.stream().toArray(PrgState[]::new)));
    }

    private IList<PrgState> removeCompletedPrg(IList<PrgState> inPrgList) {
        return new ProgramList<PrgState>(inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .toArray(PrgState[]::new));
    }

    public void allStep() {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        IList<PrgState> prgList=removeCompletedPrg(repository.getPrgList());
        while(prgList.size() > 0){
            conservativeGarbageCollector(prgList);
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();
        repository.setPrgList(prgList);
    }


    /*public void allStep() throws ProgramException{
        PrgState prg = repository.getCrtPrg(); // repo is the controller field of type RepoInterface
        repository.logPrgStateExec();

        while (!prg.getStk().isEmpty()) {
            try
            {
                PrgState current = oneStep(prg);

                prg.getHeap().setContent(unsafeGarbageCollector(
                        getAddrFromSymTable(prg.getSymTable().getContent().values()),
                        prg.getHeap().getContent()));

                if(!displayFlag) continue;

                repository.logPrgStateExec();
            }
            catch (ProgramException exception)
            {
                System.out.println(exception.toString());
            }
        }
        repository.logPrgStateExec();
        System.out.println(prg.getOutTable().toString());
    }*/
}
