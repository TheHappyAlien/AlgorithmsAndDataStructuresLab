package com.company;

import java.util.List;

public interface Generator<T> {
    List<T> generate();
}
