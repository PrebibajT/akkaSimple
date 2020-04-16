package tudo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Mensagem {

    public static class bate implements Serializable {
        private String text;
        private Long type;


        public bate(String text, Long type) {
            this.text = text;
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public Long getType() {
            return type;
        }

        public Long setType(Long type) {
        return  this.type = type;
        }
    }


    public static class rebate implements Serializable {
        private String text;
        private Long type;

        public rebate(String text, Long type) {
            this.text = text;
            this.type = type;

        }

        public String getText() {
            return text;
        }
        public Long getType() {
            return type;
        }

        public Long setType(Long type) {
            return  this.type = type;
        }


    }

}

