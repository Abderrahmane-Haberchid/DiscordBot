import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.channel.ChannelEvent;
import discord4j.core.event.domain.guild.MemberJoinEvent;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.TextChannel;
import reactor.core.publisher.Mono;


public class MainApplication {
    public static void main(String[] args) {

        String token = "MTMzNzE1MjQ0MDgzNDcyMzk1MQ.Gr_Dj6.k9n0oKRauO3LradP_VI0O4EvWEj_qw6YE8KQe0";

        DiscordClient client = DiscordClient.create(token);
        GatewayDiscordClient gateway = client.gateway()
                .login()
                .block();

        // Événement : Lorsque le bot est prêt
        gateway.on(ReadyEvent.class).subscribe(event -> {
            User self = event.getSelf();
            System.out.println("Bot connecté en tant que " + self.getUsername());
        });

        // /sayhello command
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
                Message message = event.getMessage();
                System.out.println("Message recu est => " + message.getContent());
                System.out.println("De la part du user => " + message.getAuthor().get().getUsername());
                Guild guild = event.getGuild().block();
                System.out.println("Guild est => " + guild.getName());


                    if (message.getContent().equalsIgnoreCase("/sayhello")) {
                        event.getMessage().getChannel().flatMap(channel ->
                                channel.createMessage("Hello " + message.getAuthor().get().getUsername())
                        ).onErrorResume(error -> {
                            System.out.println("Erreur lors de l'envoi");
                            return Mono.empty();
                        }).subscribe();
                    }
            });

        // Reagir a lajout dun nouveau membre
        gateway.on(MemberJoinEvent.class).subscribe(event -> {
            Member member = event.getMember();
            Guild guild = member.getGuild().block(); // ici je bloque pour obtenir le guild concerne

            TextChannel textChannel = guild.getChannels()
                    .ofType(TextChannel.class)
                    .filter(channel -> channel.getName().equalsIgnoreCase("serveur de abdo"))
                    .blockFirst();
            if (textChannel != null)
                textChannel.createMessage("Bienvenue sur le serveur de abdo "+ member.getUsername()).subscribe();

            System.out.println("Un nouveau membre est maintenent ajouté " + member.getUsername());
        });

        // reagir a la creation d'un nouveau canal
        gateway.getEventDispatcher().on(ChannelEvent.class).subscribe(channelEvent -> {
            channelEvent.getClient();
        });

        // Garder le bot actif
        gateway.onDisconnect().block();
    }
}
