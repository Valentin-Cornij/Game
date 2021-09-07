public class NotCorrectAnswer extends Exception {

    @Override
    public String toString() {
        return "Нет такого варианта ответа. Попробуй ещё раз";
    }
}
