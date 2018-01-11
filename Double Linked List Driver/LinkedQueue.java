/**
 * Created by kfo21 on 6/15/2017.
 */
public class LinkedQueue<T> implements QueueInterface<T>
{
    protected DLLNode<T> front;     // reference to the front of this queue
    protected DLLNode<T> rear;      // reference to the rear of this queue
    protected int numElements = 0; // number of elements in this queue

    public LinkedQueue()
    {
        front = null;
        rear = null;
    }

    public void enqueue(T element)
    // Adds element to the rear of this queue.
    {
        DLLNode<T> newNode = new DLLNode<T>(element);
        if (rear == null) {
            front = newNode;
        }
        else {
            rear.setBack(newNode);
            newNode.setForward(newNode);
        }
        rear = newNode;
        numElements++;
    }

    public T dequeue() throws QueueUnderflowException
    // Throws QueueUnderflowException if this queue is empty;
    // otherwise, removes front element from this queue and returns it.
    {
        if (isEmpty()) {
            throw new QueueUnderflowException("Dequeue attempted on empty queue.");
        }
        else {
            T element;
            element = front.getInfo();
            front = front.getBack();
            front.setForward(null);
            if (front == null)
                rear = null;
            numElements--;
            return element;
        }
    }

    public boolean isEmpty()
    // Returns true if this queue is empty; otherwise, returns false.
    {
        return (front == null);
    }

    public boolean isFull()
    // Returns false - a linked queue is never full.
    {
        return false;
    }

    public int size()
    // Returns the number of elements in this queue.
    {
        return numElements;
    }

}

