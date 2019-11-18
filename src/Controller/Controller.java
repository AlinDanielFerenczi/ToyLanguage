package Controller;

import Model.ADT.IStack;
import Model.PrgState;
import Model.ProgramException;
import Model.Statement.IStmt;
import Repository.IRepository;

public class Controller {
    private IRepository repository;
    private boolean displayFlag;

    public Controller(IRepository target, boolean display) {
        repository = target;
        displayFlag = display;
    }

    private PrgState oneStep(PrgState state) throws ProgramException {
        IStack<IStmt> stk= state.getStk();
        if(stk.isEmpty())
            throw new ProgramException("Prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws ProgramException{
        PrgState prg = repository.getCrtPrg(); // repo is the controller field of type RepoInterface
        repository.logPrgStateExec();

        while (!prg.getStk().isEmpty()) {
            try
            {
                PrgState current = oneStep(prg);

                if(!displayFlag) continue;

                repository.logPrgStateExec();
            }
            catch (ProgramException exception)
            {
                System.out.println(exception.toString());
            }
        }
        System.out.println(prg.getOutTable().toString());
    }
}
