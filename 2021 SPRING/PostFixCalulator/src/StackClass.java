public class StackClass {
        private  int maxStackSize;    //variable to store the maximum
        //stack size
        private  int stackTop;        //variable to point to the top
        //of the stack
        private  DataElement[] list;  //array of reference variables

        //default constructor
        //Create an array of size 100 to implement the stack.
        //Postcondition: The variable list contains the base
        //               address of the array, stackTop = 0, and
        //               maxStackSize = 100.
        public StackClass()
        {
            maxStackSize = 100;
            stackTop = 0;         //set stackTop to 0
            list = new DataElement[maxStackSize];   //create the array
        }//end default constructor

        //constructor with a parameter
        //Create an array of size stackSize to implement the stack.
        //Postcondition: The variable list contains the base
        //               address of the array, stackTop = 0, and
        //               maxStackSize = stackSize.
        public StackClass(int stackSize)
        {
            if(stackSize <= 0)
            {
                System.err.println("The size of the array to implement "
                        + "the stack must be positive.");
                System.err.println("Creating an array of size 100.");

                maxStackSize = 100;
            }
            else
                maxStackSize = stackSize;   //set the stack size to
            //the value specified by
            //the parameter stackSize
            stackTop = 0;    //set stackTop to 0
            list = new DataElement[maxStackSize]; //create the array
        }//end constructor

        //Method to initialize the stack to an empty state.
        //Postcondition: stackTop = 0
        public void initializeStack()
        {
            for(int i = 0; i < stackTop; i++)
                list[i] = null;
            stackTop = 0;
        }//end initializeStack


        //Method to determine whether the stack is empty.
        //Postcondition: Returns true if the stack is empty;
        //               otherwise, returns false.
        public boolean isEmptyStack()
        {
            return (stackTop == 0);
        }//end isEmptyStack

        //Method to determine whether the stack is full.
        //Postcondition: Returns true if the stack is full;
        //               otherwise, returns false.
        public boolean isFullStack()
        {
            return (stackTop == maxStackSize);
        }//end isFullStack

        //Method to add newItem to the stack.
        //Precondition: The stack exists and is not full.
        //Postcondition: The stack is changed and newItem
        //               is added to the top of stack.
        //               If the stack is full, the method throws
        //               StackOverflowException
        public void push(DataElement newItem) throws StackOverflowException
        {
            if(isFullStack())
                throw new StackOverflowException();

            list[stackTop] = newItem.getCopy(); //add newItem at the
            //top of the stack
            stackTop++;                         //increment stackTop
        }//end push


        //Method to return the top element of the stack.
        //Precondition: The stack exists and is not empty.
        //Postcondition: If the stack is empty, the method throws
        //               StackUnderflowException; otherwise, a
        //               reference to a copy of the top element
        //               of the stack is returned.
        public DataElement top() throws StackUnderflowException
        {
            if(isEmptyStack())
                throw new StackUnderflowException();

            DataElement temp = list[stackTop - 1].getCopy();

            return temp;
        }//end top

        //Method to remove the top element of the stack.
        //Precondition: The stack exists and is not empty.
        //Postcondition: The stack is changed and the top
        //               element is removed from the stack.
        //               If the stack is empty, the method throws
        //               StackUnderflowException
        public void pop() throws StackUnderflowException
        {
            if(isEmptyStack())
                throw new StackUnderflowException();

            stackTop--;       //decrement stackTop
            list[stackTop] = null;
        }//end pop


        //Method to make a copy of otherStack.
        //This method is used only to implement the methods
        //copyStack and copy constructor
        //Postcondition: A copy of otherStack is created and
        //               assigned to this stack.
        private void copy(StackClass otherStack)
        {
            list = null;
            System.gc();

            maxStackSize = otherStack.maxStackSize;
            stackTop = otherStack.stackTop;

            list = new DataElement[maxStackSize];

            //copy otherStack into this stack
            for(int i = 0; i < stackTop; i++)
                list[i] = otherStack.list[i].getCopy();
        }//end copy


        //copy constructor
        public StackClass(StackClass otherStack)
        {
            copy(otherStack);
        }//end constructor

        //Method to make a copy of otherStack.
        //Postcondition: A copy of otherStack is created and
        //               assigned to this stack.
        public void copyStack(StackClass otherStack)
        {
            if(this != otherStack) //avoid self-copy
                copy(otherStack);
        }//end copyStack


    }// end of StackClass
