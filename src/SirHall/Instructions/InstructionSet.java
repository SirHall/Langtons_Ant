package SirHall.Instructions;

import java.util.ArrayList;
import java.awt.*;

import SirHall.Instructions.Runnable.Instruction;

public class InstructionSet {
    protected ArrayList<InstructionColorConversion> instructionSet = new ArrayList<InstructionColorConversion>();
    protected boolean wrapped = false;

    /**
     * Add an instruction to the set
     * @param instruction
     * @param toColor
     */
    public void AddToSet(Instruction instruction, Color toColor){
//        System.out.println("Added");
        if(wrapped)
            return;

        for(int i = 0; i < instructionSet.size(); i++)
            if(instructionSet.get(i).GetToColor() == toColor)
                return; //Only continue if our set does not contain the same 'fromcolor's'

        instructionSet.add(
                new InstructionColorConversion(
                        GetFromColor(),
                        toColor,
                        instruction
                )
        );
    }

    protected Color GetFromColor(){
        return
                instructionSet.size() == 0?
                        Color.BLACK //Initial color is black
                        :
                        instructionSet.get(instructionSet.size() - 1).GetToColor();
    }

    /**
     * Used when all instructions have been added to the set.
     * At this point no more instructions can be added
     */
    public void WrapUpSet(){
        wrapped = true;
        //Set [last].toColor = [first].fromColor
        instructionSet
                .get(instructionSet.size() - 1)
                .SetToColor(
                        instructionSet
                                .get(0)
                                .GetFromColor()
                );
    }

    /**
     * Get the instruction associated with the current color
     * @param currentColor
     * @return
     */
    public InstructionColorConversion GetInstruction(Color currentColor){
//        System.out.println("Size: " + instructionSet.size());
        for(int i = 0; i < instructionSet.size(); i++)
            if(instructionSet.get(i).GetFromColor().getRGB() == currentColor.getRGB())
                return instructionSet.get(i);
//         throw new Exception("Could not find instruction-color pair!");
        return null;
    }

    /**
     * Prints data to terminal
     */
    public void Print(){
        for(int i = 0; i < instructionSet.size(); i++) {
            InstructionColorConversion instructionColorConversion = instructionSet.get(i);
            System.out.println(
                    instructionColorConversion.GetFromColor() +
                    " -> " +
                    instructionColorConversion.GetToColor()
            );
        }
    }
}
