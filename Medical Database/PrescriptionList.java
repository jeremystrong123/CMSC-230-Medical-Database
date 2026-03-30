public class PrescriptionList {
    //instance variables
    private ListRecord head;
    private ListRecord current;

    //constructors
    /**
     * Constructs a new PrescriptionList object with the head set to null.
     */
    public PrescriptionList() {
        head = null;
    }

    //methods
    /**
     * Adds a given prescription to the prescription list.
     * Adds the prescription in order from most recent date to oldest date.
     * 
     * @param pr prescription to be added to the prescription list.
     */
    public void add(Prescription pr) {
        ListRecord newRecord = new ListRecord(pr);
        if (head == null) {
            head = newRecord;
            return;
        }
        else if (newRecord.comesBefore(pr, head.data)) {
            newRecord.next = head;
            head = newRecord;
            return;
        }
        ListRecord pBefore = head;
        while (pBefore.comesBefore(pBefore.data, newRecord.data)) {
            pBefore = pBefore.next;
        }
        newRecord.next = pBefore.next;
        pBefore.next = newRecord;
        return;
    }

    /**
     * Initializes iteration from the head of the linked list, setting the current to the head
     * if the linked list has any objects, and keeping it as null if the list is empty.
     */
    public void initIteration() {
        if (head != null) {
            current = head;
        }
    }

    /**
     * Finds the current item in the list and iterates to the next item.
     * @return returns the current item if not at the end of the list, returns null if at the end of the list.
     */
    public Prescription next() {
        Prescription temp = current.data;
        current = current.next;
        if (current == null) {
            return null;
        }
        return temp;
    }

    //nested class
    private class ListRecord {
        //instance variables
        public Prescription data;
        public ListRecord next;

        //constructors
        /**
         * constructs a ListRecord with data being the given prescription and next being set to null.
         * @param pr the prescription stored in this piece of the list.
         */
        public ListRecord(Prescription pr) {
            data = pr;
            next = null;
        }

        //methods
        /**
         * compares 2 given prescriptions to find which comes before in the list (more recent date).
         * @param pr1 first prescription to compare
         * @param pr2 second prescription to compare
         * @return returns true if pr1 is more recent returns false if not.
         */
        private boolean comesBefore(Prescription pr1, Prescription pr2) {
            if (pr1.getDateOfIssue().compareTo(pr2.getDateOfIssue()) > 0) {
                return true;
            }
            return false;
        }
    }
}
