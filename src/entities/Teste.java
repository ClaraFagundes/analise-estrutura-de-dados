package entities;

public class Teste {

    /*
    public void bubbleSort() {
        int in, out;

        for (out = nElems - 1; out >= 1; out--)
            for (in = 0; in < out; in++)
                if (a[in] > a[in+1])
                    swap(in, in+1);_
    }

    public void selectionSort() {
        int out, in, min;

        for (out = 0; out < nElems - 1; out++) {
            min = out;
            for (in = out + 1; in < nElems-1; in++)
                if (a[in] < a[min])
                    min = in;
            swap(out, min);
        }
    }

    public void insertionSort() {
        int in, out;

        for (out = 1; out < nElems; out++) {
            long temp = a[out];
            in = out;
            while (in > 0 && a[in-1] >= temp) {
                a[in] = a[in-1];
                --in;
            }
            a[in] = temp;
        }
    }

    public void shellSort() {
        int inner, outer;
        long temp;

        int h = 1;
        while (h <= nElems/3)
            h = h*3 + 1;

        while (h > 0) {
            for (outer = h; outer < nElems; outer++) {
                temp = a[outer];
                inner = outer;

                while (inner > h-1 && a[inner-h] >= temp) {
                    a[inner] = a[inner-h];
                    inner -= h;
                }

                a[inner] = temp;
            }
            h = (h-1) / 3;
        }
    }

    public void mergeSort() {
        int[] workSpace = new int[arr.length];
        recMergeSort(workSpace, 0, nItems - 1);
    }

    public void recMergeSort(int[] workSpace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound)
            return;
        else {
            int mid = (lowerBound + upperBound) / 2;
            recMergeSort(workSpace, lowerBound, mid);
            recMergeSort(workSpace, mid + 1, upperBound);
            merge(workSpace, lowerBound, mid + 1, upperBound);
        }
    }

    public void merge(int[] workSpace, int lowPtr, int highPtr, int upperBound) {
        int j = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;

        while (lowPtr <= mid && highPtr <= upperBound) {
            if (arr[lowPtr] < arr[highPtr])
                workSpace[j++] = arr[lowPtr++];
            else
                workSpace[j++] = arr[highPtr++];
        }

        while (lowPtr <= mid)
            workSpace[j++] = arr[lowPtr++];

        while (highPtr <= upperBound)
            workSpace[j++] = arr[highPtr++];

        for (j = 0; j < n; j++)
            arr[lowerBound + j] = workSpace[j];
    }

    public void quickSort() {
        recQuickSort(0, nElems - 1);
    }

    public void recQuickSort(int left, int right) {
        if (right - left <= 0)
            return;
        else {
            long pivot = a[right];

            int partition = partitionIt(left, right, pivot);

            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;

        while (true) {

            while (a[++leftPtr] < pivot)
                ;

            while (rightPtr > 0 && a[--rightPtr] > pivot)
                ;

            if (leftPtr >= rightPtr)
                break;
            else
                swap(leftPtr, rightPtr);
        }

        swap(leftPtr, right);

        return leftPtr;
    }

    public void heapSort() {

        for (int i = nElems / 2 - 1; i >= 0; i--)
            heapify(nElems, i);

        for (int i = nElems - 1; i > 0; i--) {
            swap(0, i);
            heapify(i, 0);
        }
    }

    public void heapify(int size, int root) {

        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < size && a[left] > a[largest])
            largest = left;

        if (right < size && a[right] > a[largest])
            largest = right;

        if (largest != root) {
            swap(root, largest);
            heapify(size, largest);
        }
    }
     */
}
