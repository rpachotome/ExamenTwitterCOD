package examentwitter;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author rpachotome
 */
public class ExamenTwitter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * @author rpachotome
         */
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("EsQCjUvX7tEIvkxuFdjUlpuEf")
                    .setOAuthConsumerSecret("vXEScfBKnqTBKKVb2Rv85CDVZJkI2KZywGOebTTQHc9cTXxKHD")
                    .setOAuthAccessToken("267786857-a82pbvJ90mcVmAuRkk4hxTjetFi5PdTmyXpKQU5o")
                    .setOAuthAccessTokenSecret("rRfb5hCM3hgPcUWKTkdBw9nTRCISIgwTpGNZvlr3faQj1");

            Twitter twitter = new TwitterFactory(cb.build()).getInstance();
            Twitear(twitter);
            LineaDeTiempo(twitter);
            BuscarTwitter(twitter);
        } catch (TwitterException ex) {
            Logger.getLogger(ExamenTwitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @author rpachotome
     * @param twitter
     * @throws TwitterException Este metodo servira para buscar los twitters por
     * un parametro String
     */
    public static void BuscarTwitter(Twitter twitter) throws TwitterException {
        String post = JOptionPane.showInputDialog(null, "Escribe lo que quieres buscar en twitter");
        Query query = new Query(post);
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }

    /**
     * @author rpachotome
     * @param twitter
     * @throws TwitterException Este metodo servira para mostrar la linea de
     * tiempo de twitter
     */
    public static void LineaDeTiempo(Twitter twitter) throws TwitterException {
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":"
                    + status.getText());
        }
    }

    /**
     * @author rpachotome
     * @param twitter
     * @throws TwitterException Esta ultima aplicacion servira para poner un
     * tweet
     */
    public static void Twitear(Twitter twitter) throws TwitterException {
        String post = JOptionPane.showInputDialog(null, "Escribe lo que quieres twitear");
        Status status = twitter.updateStatus(post);
        System.out.println("actualizado correctamente el estado a [" + status.getText() + "].");
    }

}
