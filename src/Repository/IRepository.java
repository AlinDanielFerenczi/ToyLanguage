package Repository;

import Model.PrgState;
import Model.ProgramException;

public interface IRepository {
    PrgState getCrtPrg();
    void logPrgStateExec() throws ProgramException;
}
