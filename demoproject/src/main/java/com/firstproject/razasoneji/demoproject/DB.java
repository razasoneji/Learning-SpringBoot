package com.firstproject.razasoneji.demoproject;

public interface DB {
    // defining the DB so that we have a generic container and we can have flexible dependency injection.
    // devdb can be instantiated to it and also proddb can be also instantiated to it.
    String getmockdata();
}
