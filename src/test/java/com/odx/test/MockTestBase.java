package com.odx.test;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class MockTestBase {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpData();
    }

    protected void setUpData() {}
}
