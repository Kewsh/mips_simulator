package simulator.wrapper.wrappers;

import simulator.network.Link;
import simulator.wrapper.Wrapper;
import simulator.gates.combinational.And;

/*
 * RegisterFile has 48 bits of input, consisting of:
 * 5 bits for reading register 1 (0-4)
 * 5 bits for reading register 2 (5-9)
 * 5 bits for writing to register 3 (10-14)
 * 1 bit for RegWrite control signal (15)
 * 32 bit data bus for writing to register 3 (16-47)
 */
public class RegisterFile extends Wrapper{


    public RegisterFile(String label, String stream, Link... links){
        super(label, stream, links);
    }

    @Override
    public void initialize() {

        Register[] regArray = new Register[32] ;

        for (int i = 0; i < 32; i++)
            regArray[i] = new Register("Reg" + i, "33x32");

        Decoder decoder =  new Decoder("dec5x32" , "5x32" ,
                getInput(10),
                getInput(11),
                getInput(12),
                getInput(13),
                getInput(14)
        );

        Mux2x1[][] writeSelect = new Mux2x1[32][32];

        And[] ands = new And[32];
        for (int i = 0; i < 32; ++i)
            ands[i] = new And("A" + i, decoder.getOutput(i), getInput(47));

        for (int i = 0; i < 32; ++i) {
            for (int j = 0; j < 32; ++j) {
                writeSelect[i][j] = new Mux2x1("M" +i +j, "3X1", regArray[i].getOutput(j),
                        getInput(15 + j), ands[i].getOutput(0));
            }
        }
        for (int i = 0; i < 32; ++i) {
            for (int j = 0; j < 32; ++j) {
                regArray[i].addInput(writeSelect[i][j].getOutput(0));
            }
            regArray[i].addInput(getInput(48));
        }

//
        Multiplexer[] firstReadSelect = new Multiplexer[32];
        for (int i = 0; i < 32; ++i)
            firstReadSelect[i] = new Multiplexer("MUX" + i, "37X1");
        for (int i = 0; i < 32; ++i)
            for (int j = 0; j < 5; ++j)
                firstReadSelect[i].addInput(getInput(j));
        for (int i = 0; i < 32; ++i) {
            for (int j = 0; j < 32; ++j) {
                firstReadSelect[i].addInput(regArray[j].getOutput(i));
            }
            addOutput(firstReadSelect[i].getOutput(0));
        }

        Multiplexer[] secondReadSelect = new Multiplexer[32];
        for (int i = 0; i < 32; ++i)
            secondReadSelect[i] = new Multiplexer("MUX2" + i, "37X1");
        for (int i = 0; i < 32; ++i)
            for (int j = 0; j < 5; ++j)
                secondReadSelect[i].addInput(getInput(j + 5));
        for (int i = 0; i < 32; ++i) {
            for (int j = 0; j < 32; ++j) {
                secondReadSelect[i].addInput(regArray[j].getOutput(i));
            }
            addOutput(secondReadSelect[i].getOutput(0));
        }
    }
}
