package Repository;

import Model.ADT.IList;
import Model.PrgState;
import Model.ProgramException;

public interface IRepository {
    String toString();
    IList<PrgState> getPrgList();
    void logPrgStateExec(PrgState target) throws ProgramException;
    void setPrgList(IList<PrgState> newThreads);

}
