package physics;

public class PhysicsConfig {

    // poner a 0 para desactivar
    public static final double DEFAULT_GRAVITY = 500; // poner a 0 para desactivar gravedad recomendado 500

    public static final double DEFAULT_AIR_FRICTION = 0.05; // poner a 0 para desactivar recomendado 0.05
    public static final double DEFAULT_GROUND_FRICTION = 4.0; // poner a 0 para desactivar recomendado 4.0

    public static final double DEFAULT_RESTITUTION = 0.8; // rebote al maximo 1 no rebotes 0 recomendado 0.8
    
    public static final double MIN_TO_SLEEP = 0.05; //velocidad minima a la que se podra ir recomendado 0.05
}