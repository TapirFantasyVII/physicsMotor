/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package physics.solvers;

import physics.*;

/**
 *
 * @author tapir
 */
public class CircleCircleSolver {

    public static void resolvePosition(CircleBody bodyA, CircleBody bodyB) {

        double dx = bodyB.getCenterX() - bodyA.getCenterX();
        double dy = bodyB.getCenterY() - bodyA.getCenterY();

        double distSq = dx * dx + dy * dy;
        double sumRadii = bodyA.getRadius() + bodyB.getRadius();

        if (distSq >= sumRadii * sumRadii) {
            return;
        }

        double distance = Math.sqrt(distSq);

        double nx;
        double ny;

        if (distance < 1e-6) {
            nx = 1;
            ny = 0;
            distance = 0.0001;
        } else {
            nx = dx / distance;
            ny = dy / distance;
        }

        double overlap = sumRadii - distance;

        double correction
                = Math.max(overlap - PhysicsConfig.COLISION_PENETRATION, 0.0)
                * PhysicsConfig.COLISION_CORRECTION;

        double totalMass = bodyA.getMass() + bodyB.getMass();

        double moveA = correction * bodyB.getMass() / totalMass;
        double moveB = correction * bodyA.getMass() / totalMass;

        bodyA.translate(-nx * moveA, -ny * moveA);
        bodyB.translate(nx * moveB, ny * moveB);
    }

    public static void resolveVelocity(CircleBody bodyA, CircleBody bodyB) {

        double dx = bodyB.getCenterX() - bodyA.getCenterX();
        double dy = bodyB.getCenterY() - bodyA.getCenterY();

        double distSq = dx * dx + dy * dy;
        double sumRadii = bodyA.getRadius() + bodyB.getRadius();

        // Si ya no se tocan, no hay impulso
        if (distSq >= sumRadii * sumRadii) {
            return;
        }

        double distance = Math.sqrt(distSq);

        if (distance < 1e-6) {
            return;
        }

        double nx = dx / distance;
        double ny = dy / distance;

        Vector2d v1 = bodyA.getVelocity();
        Vector2d v2 = bodyB.getVelocity();

        // Velocidad relativa
        double rvx = v2.x - v1.x;
        double rvy = v2.y - v1.y;

        // Velocidad sobre la normal
        double velAlongNormal = rvx * nx + rvy * ny;

        // Ya se están separando
        if (velAlongNormal >= 0) {
            return;
        }

        double restitution = bodyA.getWorld().getRestitution();

        double invMassA = 1.0 / bodyA.getMass();
        double invMassB = 1.0 / bodyB.getMass();

        double j = -(1.0 + restitution) * velAlongNormal;
        j /= invMassA + invMassB;

        Vector2d impulse = new Vector2d(nx * j, ny * j);

        bodyA.setVelocity(
                new Vector2d(
                        v1.x - impulse.x * invMassA,
                        v1.y - impulse.y * invMassA));

        bodyB.setVelocity(
                new Vector2d(
                        v2.x + impulse.x * invMassB,
                        v2.y + impulse.y * invMassB));
    }
}
