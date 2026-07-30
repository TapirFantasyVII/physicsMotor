package physics;

import physics.Body;
import physics.CircleBody;
import physics.solvers.CircleCircleSolver;
public final class CollisionSolver {

    private CollisionSolver() {}

    public static void solvePosition(Body a, Body b) {

        if (a instanceof CircleBody c1 &&
            b instanceof CircleBody c2) {

            CircleCircleSolver.resolvePosition(c1, c2);
        }
    }

    public static void solveVelocity(Body a, Body b) {

        if (a instanceof CircleBody c1 &&
            b instanceof CircleBody c2) {

            CircleCircleSolver.resolveVelocity(c1, c2);
        }
    }
}