package simulator.wrapper.wrappers;

import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Register extends Wrapper{

    private DFlipFlop[] register;

    public Register(String label, String stream, Link... links){
        super(label, stream, links);
        register = new DFlipFlop[32];
    }

    @Override
    public void initialize(){
        Link clock = getInput(32);
        for (int i = 0; i < 32; i++) {
            register[i] = new DFlipFlop("DFF" + i, "2x2", clock, getInput(i));
            addOutput(register[i].getOutput(0));
        }
    }

    public void setRegister(Link[] arr){
        if (arr.length != 32)
            throw new IndexOutOfBoundsException("array size must be 32");
        for (int i = 0; i < arr.length; i++)
            register[i].setInput(1, arr[i]);
    }
}
