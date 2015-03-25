package cn.arrow.brainteasing;

public abstract class Stack {
	public abstract void Push(int x) throws StackOverflowsException;
	public abstract int Pop() throws StackUnderflowsException;

}
