import controller.Controller;
import model.Model;
import view.View;

public class Main {
    public static void main(String[] args) {
        final int ANCHO =1080;
        final int ALTO =720;
        Model model = new Model();
        View view = new View(model, 1080, 720);
        Controller controller = new Controller(model, view);

        view.mostrar();
        controller.startMotor(); 
    }
}
