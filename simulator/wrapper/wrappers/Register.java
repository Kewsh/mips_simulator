package simulator.wrapper.wrappers;

import simulator.network.Link;
import simulator.wrapper.Wrapper;

/*
 * Register has 33 bits of input, consisting of:
 * 32 bits of data (0-31)
 * 1 bit for clock (32)
 */
public class Register extends Wrapper{

    public Register(String label, String stream, Link... links){
        super(label, stream, links);
    }

    @Override
    public void initialize(){
        DFlipFlop[] register = new DFlipFlop[32];
        Link clock = getInput(32);
        for (int i = 0; i < 32; i++) {
            register[i] = new DFlipFlop("DFF" + i, "2x2", clock, getInput(i));
            addOutput(register[i].getOutput(0));
        }
    }
}
