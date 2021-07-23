package simulator.wrapper.wrappers.AluInstruction;

import simulator.network.Link;
import simulator.wrapper.Wrapper;
import simulator.wrapper.wrappers.FullSubtractor;
import simulator.wrapper.wrappers.HalfSubtractor;

public class Sub extends Wrapper {

    public Sub(String label, String stream, Link... links){
        super(label, stream, links);
    }

    @Override
    public void initialize() {

        FullSubtractor[] fullsubs = new FullSubtractor[31];

        HalfSubtractor hs = new HalfSubtractor("HS1","2X2", getInput(0), getInput(32));
        fullsubs[0] = new FullSubtractor("FS0","3X2", getInput(1), getInput(33), hs.getOutput(1));

        for (int i = 1; i < 31; i++)
            fullsubs[i] = new FullSubtractor(
                    "FS" + (i+1),
                    "3x2",
                    getInput(i+1),
                    getInput(32+i+1),
                    fullsubs[i-1].getOutput(1)
            );

        for (int i = 30; i >= 0; i--)
            addOutput(fullsubs[i].getOutput(0));
        addOutput(hs.getOutput(0));
    }
}
