package Model.Statement;

import Model.ADT.IStack;
import Model.PrgState;
import Model.ProgramException;

public class CompStmt implements IStmt {
        private IStmt first;
        private IStmt snd;

        public CompStmt(IStmt firstOne, IStmt secondOne)
        {
            first = firstOne;
            snd = secondOne;
        }

        public String toString() {
            return " ( Comp " +
                    first.toString() +
                    "; "
                    + snd.toString() +
                    " ) ";
        }

        public PrgState execute(PrgState state) throws ProgramException {
            IStack<IStmt> stk = state.getStk();
            stk.push(snd);
            stk.push(first);

            return state;
        }
    }
