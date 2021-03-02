package logic;

import java.io.BufferedReader;
import java.io.Reader;

public abstract class DataAccessFactory implements DataAccessInterface  {

    DataAccessFactory(){
    }
    @Override
    public BufferedReader streamFactory(Gender gender) {
        return new BufferedReader(Reader.nullReader());
    }
}
