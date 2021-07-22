package simulator.wrapper.wrappers.AluInstruction;

import simulator.network.Link;
import simulator.wrapper.Wrapper;
import simulator.wrapper.wrappers.FullAdder;
import simulator.wrapper.wrappers.HalfAdder;

public class Add extends Wrapper {

    public Add(String label, String stream, Link... links) {
        super(label, stream, links);
    }

    @Override
    public void initialize() {

        FullAdder[] FullAdders = new FullAdder[32];

        HalfAdder ha = new HalfAdder("HA", "2X2", getInput(0), getInput(32));
        FullAdders[0] = new FullAdder("FA1", "3X2", getInput(1), getInput(33), ha.getOutput(0));

        for (int i = 1; i < 31; i++)
            FullAdders[i] = new FullAdder(
                    "FA" + (i+1),
                    "3x2",
                    getInput(i+1),
                    getInput(32+i+1),
                    FullAdders[i-1].getOutput(0)
            );

        for (int i = 30; i >= 0; i--)
            addOutput(FullAdders[i].getOutput(1));
        addOutput(ha.getOutput(1));
    }
}
