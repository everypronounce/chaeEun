package com.example.todayspeaking;

public class Message {
    public String sentence;

    public Message(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sentence='" + sentence + '\'' +
                '}';
    }
}