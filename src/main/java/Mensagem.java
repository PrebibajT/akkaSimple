import java.io.Serializable;

public class Mensagem  {

    public static class bate implements Serializable{
        private  String text;

        public bate(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }


    public static class rebate implements Serializable{
        private  String text;

        public rebate(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}

