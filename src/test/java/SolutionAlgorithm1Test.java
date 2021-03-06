
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SolutionAlgorithm1Test {

    @Test
    public void test_check_reverse_point(){
        SolutionAlgorithm1 solutionAlgorithm1 = new SolutionAlgorithm1();

        Coordinate point0 = new Coordinate(5, 1);
        Coordinate point1 = new Coordinate(1, 7);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.X, -1));
        steps.add(new Step(Axis.X, -3, 1));
        steps.add(new Step(Axis.Y, 3, 2));

        List<Step> stepsRequest =  solutionAlgorithm1.algorithm_1(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_with_min_length(){

        SolutionAlgorithm1 solutionAlgorithm1 = new SolutionAlgorithm1();

        Coordinate point0 = new Coordinate(1, 1);
        Coordinate point1 = new Coordinate(2, 2);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.X, 1, 1));
        steps.add(new Step(Axis.Y, 1, 1));

        List<Step> stepsRequest =  solutionAlgorithm1.algorithm_1(point0, point1);

        assertThat(stepsRequest, is(steps));

    }

    @Test
    public void test_with_same_axis(){

        SolutionAlgorithm1 solutionAlgorithm1 = new SolutionAlgorithm1();

        Coordinate point0 = new Coordinate(1, 5);
        Coordinate point1 = new Coordinate(1, 2);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.Y, -3, 1));

        List<Step> stepsRequest =  solutionAlgorithm1.algorithm_1(point0, point1);

        assertThat(stepsRequest, is(steps));

    }

    @Test
    public void test_with_minus_min(){

        SolutionAlgorithm1 solutionAlgorithm1 = new SolutionAlgorithm1();

        Coordinate point0 = new Coordinate(2, 2);
        Coordinate point1 = new Coordinate(1, 1);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.Y, -1));
        steps.add(new Step(Axis.X, -1));

        List<Step> stepsRequest =  solutionAlgorithm1.algorithm_1(point0, point1);

        assertThat(stepsRequest, is(steps));
    }
}