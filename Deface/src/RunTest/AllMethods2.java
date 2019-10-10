package RunTest;

public interface AllMethods2 {
	boolean betterVal(TermState temp , TermState ret);
	boolean worseVal(TermState temp, TermState ret);
	TermState terminalTest(CellVal[][] tempState);
	TicTacNode findMax();
	TicTacNode findMin();

}
