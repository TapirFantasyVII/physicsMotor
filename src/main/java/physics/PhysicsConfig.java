package physics;

public class PhysicsConfig {
    
    // poner a 0 para desactivar
    public static double GRAVITY = 500; //poner a 0 para desactivar gravedad recomendado 500
 
    public static double AIR_FRICTION = 0.0; //poner a 0 para desactivar recomendado 0.05
    public static double GROUND_FRICTION = 0; //poner a 0 para desactivar recomendado 4.0

    public static double RESTITUTION = 1.0;      // rebote al maximo 1 no rebotes 0 recomendado 0.8
    
    
    
    public static final Vector2d GRAVITY_VECTOR = new Vector2d(0,GRAVITY);
}