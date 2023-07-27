package com.example.helloworld;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyCommandLineRunnerTest {

    @Mock
    private MyCommandLineRunner myCommandLineRunner;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRun() throws Exception {
        myCommandLineRunner.run();
        verify(myCommandLineRunner, times(1)).run();
    }
}