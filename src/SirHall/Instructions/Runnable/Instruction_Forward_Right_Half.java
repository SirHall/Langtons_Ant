package SirHall.Instructions.Runnable;

import SirHall.Instructions.StepInfo;
import SirHall.Maths.Vector2D;

public class Instruction_Forward_Right_Half extends Instruction{
    public void Step(StepInfo stepInfo){
        stepInfo.canvasJar.ApplyBrushAtPosition(stepInfo.ant.GetPosition(), stepInfo.toColor);
        stepInfo.ant.RotateByDeg(-45);
        stepInfo.ant.AddPosition(Vector2D.Mul(stepInfo.ant.GetForward(), (float)Math.sqrt(2)));
    }
}

