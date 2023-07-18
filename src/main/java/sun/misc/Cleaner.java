package sun.misc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class Cleaner extends PhantomReference<Object> {
    private Cleaner(Object referent, ReferenceQueue<? super Object> queue) {
        super(referent, queue);
    }

    public static Cleaner create(Object ob, Runnable thunk) {
        throw new UnsupportedOperationException("Stub!");
    }

    public void clean() {
        throw new UnsupportedOperationException("Stub!");
    }
}
