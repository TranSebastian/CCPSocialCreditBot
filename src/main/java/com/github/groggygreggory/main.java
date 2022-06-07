package com.github.groggygreggory;

import org.javacord.api.*;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.user.User;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class main {

    public static void main(String[] args) throws IOException {
        BadWords badWords = new BadWords();

        String token = "ODk4ODAxNTc4NTA3NjU3MjQ2.YWpgMw.YxUKkUT-CFQV7vM1yLb3ykNP6AU";

        SebastianTop10 sebastian = new SebastianTop10();

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        api.addMessageCreateListener(event -> {
            MessageAuthor author = event.getMessage().getAuthor();

            if (event.getMessageContent().equalsIgnoreCase("!register")) {

                if (!sebastian.names().contains(author.getDisplayName())){
                    try {
                        sebastian.updateScoreList(10, author.getDisplayName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    event.getChannel().sendMessage(author.getDisplayName() + " added!");
                }
                else{
                    event.getChannel().sendMessage(author.getDisplayName() +" already registered!");
                }
            }
            else if (event.getMessageContent().equalsIgnoreCase("!getScore")) {
                if (sebastian.names().contains(author.getDisplayName())){
                    event.getChannel().sendMessage(sebastian.info(author.getDisplayName()) + " social credit");
                }
                else{
                    event.getChannel().sendMessage(author.getDisplayName() +" not added!");
                }
            }
            else if (badWords.containsBadPhrase(event.getMessageContent()) && sebastian.names().contains(author.getDisplayName())){
                try {
                    sebastian.updateScoreList(sebastian.scores(sebastian.names().indexOf(author.getDisplayName())) - (int) (Math.random()*100) +1,
                            sebastian.names(sebastian.names().indexOf(author.getDisplayName())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new MessageBuilder().addAttachment(new File("bad.jpg")).append("不好！").send(event.getChannel());
            }
            else if (badWords.containsGoodPhrase(event.getMessageContent()) && sebastian.names().contains(author.getDisplayName())){
                try {
                    sebastian.updateScoreList(sebastian.scores(sebastian.names().indexOf(author.getDisplayName())) + (int) (Math.random()*100) +1,
                            sebastian.names(sebastian.names().indexOf(author.getDisplayName())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new MessageBuilder().addAttachment(new File("good.jpg")).append("不好！").send(event.getChannel());

            }




        });



    }


}
