package physics.solvers;

import physics.Body;
import physics.Vector2d;
import physics.World;

public final class WorldSolver {

    private WorldSolver() {
    }

    public static void solve(Body body, World world) {

        double x = body.getX();
        double y = body.getY();

        Vector2d velocity = body.getVelocity();

        double width = body.getWidth();
        double height = body.getHeight();

        double restitution = world.getRestitution();

        // izquierda
        if (x < 0) {

            body.translate(-x, 0);

            velocity.x *= -restitution;
        }

        // derecha
        if (x + width > world.getWidth()) {

            body.translate(world.getWidth() - (x + width), 0);

            velocity.x *= -restitution;
        }

        // techo
        if (y < 0) {

            body.translate(0, -y);

            velocity.y *= -restitution;
        }

        // suelo
        if (y + height > world.getHeight()) {

            body.translate(0, world.getHeight() - (y + height));

            velocity.y *= -restitution;
        }

        body.setVelocity(velocity);
    }

}