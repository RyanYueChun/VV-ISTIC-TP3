package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {
        SSLSocket socketMock = Mockito.mock(SSLSocket.class);

        // Given
        when(socketMock.getSupportedProtocols()).thenReturn(null);
        when(socketMock.getEnabledProtocols()).thenReturn(null);

        // When
        TLSSocketFactory factory = new TLSSocketFactory();
        factory.prepareSocket(socketMock);

        // Then
        // check that we never call the method "setEnabledProtocols"
        verify(socketMock, never()).setEnabledProtocols(any());
    }

    @Test
    public void typical() {
        SSLSocket socketMock = Mockito.mock(SSLSocket.class);

        // Given
        String[] supportedProtocols = new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
        String[] enabledProtocols = new String[]{"SSLv3", "TLSv1"};

        when(socketMock.getEnabledProtocols()).thenReturn(enabledProtocols);
        when(socketMock.getSupportedProtocols()).thenReturn(supportedProtocols);

        // when
        TLSSocketFactory factory = new TLSSocketFactory();
        factory.prepareSocket(socketMock);


        // then
        // check that we call the method one time
        // and also check if we pass the right params
        String[] expectedParam = new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" };
        verify(socketMock, times(1)).setEnabledProtocols(expectedParam);


    }

}