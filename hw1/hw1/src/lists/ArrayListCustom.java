package lists;
import java.util.Collection;
import java.util.Comparator;
import java.util.Arrays;

public class ArrayListCustom<T> {
    private Object[] data;
    private int size;
    private int curr_elems;

    public ArrayListCustom() {
        size = 10;
        curr_elems = 0;
        data = new Object[10];
    }

    public int getSize() {
        // return number of elements
        return curr_elems;
    }
    public void add(int index, T element) {
        try {
            if (curr_elems == size) {
                size = (int) (size * 1.5 + 1);
            }
            Object[] newData = new Object[size];
            if (index == curr_elems) {
                System.arraycopy(data, 0, newData, 0, curr_elems);
                newData[index] = element;
                curr_elems++;
            }
            if (index > curr_elems ) {
                throw new Exception("Неверный индекс");
            }
            else {
                System.arraycopy(data, 0, newData, 0, index);
                newData[index] = element;
                System.arraycopy(data, index, newData, index + 1, curr_elems - index);
            }
            data = newData;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public T get(int index) {
        return (T) (data[index]);
    }
    public void addAll(Collection<? extends T> c) {
        if (size < curr_elems + c.size()) {
            size = curr_elems + c.size() + 10;
        }
        Object[] newData = new Object[size];
        System.arraycopy(data, 0, newData, 0, curr_elems);
        System.arraycopy(c.toArray(), 0, newData, curr_elems, c.size());
        curr_elems += c.size();
        data = newData;
    }
    public void clear() {
        size = 10;
        data = new Object[10];
        curr_elems = 0;
    }
    public boolean isEmpty() {
        return curr_elems == 0;
    }
    public void remove(int index) {
        Object[] newData =  new Object[size];
        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(data, index + 1, newData, index, curr_elems - index);
        curr_elems--;
        data = newData;
    }
    public void remove(Object o) {
        int index = Arrays.asList(data).indexOf(o);
        remove(index);
    }
    public void sort(Comparator<? super T> c) {
        data = mergeSort(data, c);
        Object[] newData = new Object[size];
        System.arraycopy(data, curr_elems, newData, 0, curr_elems);
        data = newData;
    }
    private Object[] mergeSort(Object[] src, Comparator<? super T> c) {
        if (src.length <= 1) return src;
        Object[] left = Arrays.copyOfRange(src, 0, src.length / 2);
        Object[] right = Arrays.copyOfRange(src, left.length, src.length);
        return merge(mergeSort(left, c), mergeSort(right, c), c);
    }
    private Object[] merge(Object[] left, Object[] right, Comparator<? super T> c) {
        int resIn = 0, leftIn = 0, rightIn = 0;
        Object[] result = new Object[left.length + right.length];
        while (leftIn < left.length && rightIn < right.length)
            if (c.compare((T)(left[leftIn]), (T)(right[rightIn])) < 0)
                result[resIn++] = left[leftIn++];
            else result[resIn++] = right[rightIn++];

        while (resIn < result.length)
            if (leftIn != left.length)
                result[resIn++] = left[leftIn++];
            else result[resIn++] = right[rightIn++];
        return result;
    }
}
