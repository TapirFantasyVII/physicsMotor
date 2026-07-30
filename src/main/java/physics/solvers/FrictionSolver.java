package physics.solvers;

import physics.Body;
import physics.Vector2d;
import physics.World;

public final class FrictionSolver {

    private static final double EPSILON = 0.5;

    private FrictionSolver() {
    }

    public static void solve(Body body, World world) {

        boolean grounded =
                body.getY() + body.getHeight() >= world.getHeight() - EPSILON;

        if (grounded) {

            double fx = -body.getVelocity().x * world.getGroundFriction();

            body.applyForce(new Vector2d(fx, 0));

        } else {

            body.applyForce(
                    body.getVelocity().scale(-world.getAirFriction()));
        }

    }

}