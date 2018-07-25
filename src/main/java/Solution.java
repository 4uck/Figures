import java.util.ArrayList;
import java.util.List;

public class Solution {

    private int dymax = 3;
    private int dxmax = 3;

    public List<Step> algorithm_1(
            Coordinate point0,
            Coordinate point1
    ){

        List<Step> steps = new ArrayList<>();

        int diffX = point1.getX() - point0.getX();
        int diffY = point1.getY() - point0.getY();


        if (diffX != 0) {
            int countStepsX = diffX / dxmax;
            int lastStepLengthX = diffX % dxmax;

            if (countStepsX != 0) {

                int max = dxmax;

                if (countStepsX < 0) {
                    max = -max;

                    Step step = new Step(Axis.X, max, -countStepsX);
                    steps.add(0, step);
                }else {
                    Step step = new Step(Axis.X, max, countStepsX);
                    steps.add(step);
                }
            }

            if (lastStepLengthX != 0){
                Step lastStep = new Step(Axis.X, lastStepLengthX);

                if (lastStepLengthX < 0)
                    steps.add(0, lastStep);
                else
                    steps.add(lastStep);
            }
        }

        if (diffY != 0) {
            int countStepsY = diffY / dymax;
            int lastStepLengthY = diffY % dymax;

            if (countStepsY != 0) {

                int max = dymax;

                if (countStepsY < 0) {
                    max = -max;

                    Step step = new Step(Axis.Y, max, -countStepsY);
                    steps.add(0, step);
                }else {
                    Step step = new Step(Axis.Y, max, countStepsY);
                    steps.add(step);
                }
            }

            if (lastStepLengthY != 0){
                Step lastStep = new Step(Axis.Y, lastStepLengthY);

                if (lastStepLengthY < 0)
                    steps.add(0, lastStep);
                else
                    steps.add(lastStep);
            }
        }

        return steps;
    }
}
