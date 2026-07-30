package physics.solvers;

import physics.Body;
import physics.PhysicsConfig;
import physics.Vector2d;

public final class SleepSolver {

    private SleepSolver() {
    }

    public static void solve(Body body) {

        Vector2d v = body.getVelocity();

        if (Math.abs(v.x) < PhysicsConfig.MIN_TO_SLEEP) {
            v.x = 0;
        }

        if (Math.abs(v.y) < PhysicsConfig.MIN_TO_SLEEP) {
            v.y = 0;
        }

        body.setVelocity(v);

    }

}