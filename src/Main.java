public class Main {
    public static void main(String[] args) {
        Model model = new Model();

        TextView view = new TextView();

        Controller controller = new Controller(model, view);

        controller.startSession();
    }
}
