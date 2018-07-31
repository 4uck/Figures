import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Solution2Test {

    private static ConvexArea AREA;

    @BeforeClass
    public static void setUp() throws Exception {
        AREA = new ConvexArea(
                new Coordinate(0, 10),
                new Coordinate(10, 0));
    }

    @Test
    public void test_1(){
        Solution2 solution2 = new Solution2(5, 5, 3, 4, AREA);

        Coordinate point0 = new Coordinate(2, 2);
        Coordinate point1 = new Coordinate(8, 1);

        List<Step> steps = new ArrayList<>();


        steps.add(new Step(Axis.Y, 4));
        steps.add(new Step(Axis.Y, -5));
        steps.add(new Step(Axis.X, 3, 2));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_2(){
        Solution2 solution2 = new Solution2(5, 5, 4, 4, AREA);

        Coordinate point0 = new Coordinate(0, 2);
        Coordinate point1 = new Coordinate(17, 1);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.X, 5, 1));
        steps.add(new Step(Axis.X, 4, 3));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_3(){
        Solution2 solution2 = new Solution2(5, 5, 1, 1, AREA);

        Coordinate point0 = new Coordinate(0, 2);
        Coordinate point1 = new Coordinate(12, 1);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.Y, -1, 1));
        steps.add(new Step(Axis.X, 2, 1));
        steps.add(new Step(Axis.X, 5, 2));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_with_minus_x_excess(){
        Solution2 solution2 = new Solution2(5, 5, 4, 4, AREA);

        Coordinate point0 = new Coordinate(12, 1);
        Coordinate point1 = new Coordinate(0, 5);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.X, -4, 3));
        steps.add(new Step(Axis.Y, 4, 1));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_with_minus_y_excess(){
        Solution2 solution2 = new Solution2(5, 5, 4, 4, AREA);

        Coordinate point0 = new Coordinate(1, 12);
        Coordinate point1 = new Coordinate(5, 0);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.Y, -4, 3));
        steps.add(new Step(Axis.X, 4, 1));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_with_double_minus_excess(){
        Solution2 solution2 = new Solution2(5, 5, 1, 1, AREA);

        Coordinate point0 = new Coordinate(2, 2);
        Coordinate point1 = new Coordinate(1, 1);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.Y, -1, 1));
        steps.add(new Step(Axis.X, -1, 1));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_4(){
        Solution2 solution2 = new Solution2(5, 5, 1, 4, AREA);

        Coordinate point0 = new Coordinate(3, 5);
        Coordinate point1 = new Coordinate(2, 6);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.X, -1));
        steps.add(new Step(Axis.Y, -4));
        steps.add(new Step(Axis.Y, 5));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_5(){
        Solution2 solution2 = new Solution2(5, 5, 1, 2, AREA);

        Coordinate point0 = new Coordinate(3, 1);
        Coordinate point1 = new Coordinate(2, 2);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.X, -1));
        steps.add(new Step(Axis.Y, 2));
        steps.add(new Step(Axis.Y, 2));
        steps.add(new Step(Axis.Y, -3));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_6(){
        Solution2 solution2 = new Solution2(5, 5, 1, 3, AREA);

        Coordinate point0 = new Coordinate(3, 1);
        Coordinate point1 = new Coordinate(2, 2);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.X, -1));
        steps.add(new Step(Axis.Y, 3));
        steps.add(new Step(Axis.Y, 3));
        steps.add(new Step(Axis.Y, -5));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_7(){
        Solution2 solution2 = new Solution2(4, 4, 3, 1, AREA);

        Coordinate point0 = new Coordinate(3, 4);
        Coordinate point1 = new Coordinate(4, 3);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.Y, -1));
        steps.add(new Step(Axis.X, -3));
        steps.add(new Step(Axis.X, 4));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_8(){
        Solution2 solution2 = new Solution2(4, 4, 3, 1, AREA);

        Coordinate point0 = new Coordinate(0, 2);
        Coordinate point1 = new Coordinate(2, 1);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.Y, -1));
        steps.add(new Step(Axis.X, 3, 2));
        steps.add(new Step(Axis.X, -4));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

    @Test
    public void test_new_algorithm(){
        Solution2 solution2 = new Solution2(4, 4, 2, 2, AREA);

        Coordinate point0 = new Coordinate(1, 2);
        Coordinate point1 = new Coordinate(2, 1);

        List<Step> steps = new ArrayList<>();

        steps.add(new Step(Axis.Y, 2));
        steps.add(new Step(Axis.Y, -3));
        steps.add(new Step(Axis.X, 2, 2));
        steps.add(new Step(Axis.X, -3));

        List<Step> stepsRequest =  solution2.algorithm_2(point0, point1);

        assertThat(stepsRequest, is(steps));
    }

}