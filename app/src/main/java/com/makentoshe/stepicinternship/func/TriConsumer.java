package com.makentoshe.stepicinternship.func;

/**
 * Created by Makentoshe on 27.04.2018.
 */
@FunctionalInterface
public interface TriConsumer<A, B, C> {

    void accept(A a, B b, C c);
}
