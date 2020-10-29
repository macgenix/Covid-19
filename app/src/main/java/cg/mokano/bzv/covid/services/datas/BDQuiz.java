package cg.mokano.bzv.covid.services.datas;

import java.util.ArrayList;

import cg.mokano.bzv.covid.models.Quiz;

public class BDQuiz {
    public static ArrayList<Quiz>  db (){
        ArrayList<Quiz> all = new ArrayList<>();

        Quiz quiz1 = new Quiz();
        quiz1.setId(1);
        quiz1.setQuestion("Si vous pensez être atteint du coronavirus, vous devez :");
        quiz1.setRep1("contacter le 3434");
        quiz1.setRep2("Voir un géneraliste");
        quiz1.setRep3("Se rendre aux urgences");
        quiz1.setBoeRep("contacter le 3434");
        all.add(quiz1);

        Quiz quiz2 = new Quiz();
        quiz2.setId(2);
        quiz2.setQuestion("Quel est la duré moyenne d'incubation du Covid-19 ?");
        quiz2.setRep1("5 à 7 Jours");
        quiz2.setRep2("10 à 15 Jours");
        quiz2.setRep3("30 jours ou plus");
        quiz2.setBoeRep("5 à 7 Jours");
        all.add(quiz2);

        Quiz quiz3 = new Quiz();
        quiz3.setId(3);
        quiz3.setQuestion("Quel terme employé actuellement pour designer les personnes confinées par précautions ?");
        quiz3.setRep1("En couvaine");
        quiz3.setRep2("En quarantaine");
        quiz3.setRep3("En isolaine");
        quiz3.setBoeRep("En quarantaine");
        all.add(quiz3);

        Quiz quiz4 = new Quiz();
        quiz4.setId(4);
        quiz4.setQuestion("Quel animal soupsonne-t-on d'avoir transmit le coronavirus à l'homme (on parle d'hote sécondaire) ?");
        quiz4.setRep1("Le faisan");
        quiz4.setRep2("La chauve-souris");
        quiz4.setRep3("Le pangolin");
        quiz4.setBoeRep("Le pangolin");
        all.add(quiz4);

        Quiz quiz5 = new Quiz();
        quiz5.setId(5);
        quiz5.setQuestion("Quelle Categorie de personne sont les plus vulnerables ?");
        quiz5.setRep1("Les jeunes enfants");
        quiz5.setRep2("Les seniors");
        quiz5.setRep3("Les sportifs");
        quiz5.setBoeRep("Les seniors");
        all.add(quiz5);

        Quiz quiz6 = new Quiz();
        quiz6.setId(6);
        quiz6.setQuestion("Quel symptôme peut permettre de differencier le Covid-19 d'une grippe normale ?");
        quiz6.setRep1("Des difficulter respiratoires");
        quiz6.setRep2("Des vomissements");
        quiz6.setRep3("Une toux sèche");
        quiz6.setBoeRep("Des difficulter respiratoires");
        all.add(quiz6);

        Quiz quiz7 = new Quiz();
        quiz7.setId(7);
        quiz7.setQuestion("Ce nouveau virus tue...");
        quiz7.setRep1("Deux fois plus que la grippe");
        quiz7.setRep2("Dix fois plus que la grippe");
        quiz7.setRep3("Cent fois plus que la grippe");
        quiz7.setBoeRep("Dix fois plus que la grippe");
        all.add(quiz7);

        Quiz quiz8 = new Quiz();
        quiz8.setId(8);
        quiz8.setQuestion("Par precaution, lequel de ces gestes adopter principalement pour éviter la cantamination ?");
        quiz8.setRep1("Eviter les embrassades");
        quiz8.setRep2("Se faire tester");
        quiz8.setRep3("Ne rien touché");
        quiz8.setBoeRep("Eviter les embrassades");
        all.add(quiz8);

        Quiz quiz9 = new Quiz();
        quiz9.setId(9);
        quiz9.setQuestion("Combien de temps le virus peut-il survivre sur une table ou poignée de porte ?");
        quiz9.setRep1("Jusqu'à 10 minutes");
        quiz9.setRep2("Jusqu'à 3 heures");
        quiz9.setRep3("Jusqu'à 7 jours");
        quiz9.setBoeRep("Jusqu'à 3 heures");
        all.add(quiz9);

        Quiz quiz10 = new Quiz();
        quiz10.setId(10);
        quiz10.setQuestion("Quelle est la distance minimale de sécurité entre deux personnes ?");
        quiz10.setRep1("1 mètre");
        quiz10.setRep2("3 mètre");
        quiz10.setRep3("1 kilomètre");
        quiz10.setBoeRep("1 mètre");
        all.add(quiz10);

        Quiz quiz11 = new Quiz();
        quiz11.setId(11);
        quiz11.setQuestion("Quel est le nombre de cas au Congo (le 12/05/2020) ?");
        quiz11.setRep1("105");
        quiz11.setRep2("333");
        quiz11.setRep3("36");
        quiz11.setBoeRep("333");
        all.add(quiz11);

        Quiz quiz12 = new Quiz();
        quiz12.setId(12);
        quiz12.setQuestion("Quel numero composé en cas d'urgence Covid-19 au Congo ?");
        quiz12.setRep1("117");
        quiz12.setRep2("118");
        quiz12.setRep3("3434");
        quiz12.setBoeRep("3434");
        all.add(quiz12);

        Quiz quiz13 = new Quiz();
        quiz13.setId(13);
        quiz13.setQuestion("Le Congo compte combien de cas gueris à la date du 13/05/2020");
        quiz13.setRep1("53");
        quiz13.setRep2("21");
        quiz13.setRep3("333");
        quiz13.setBoeRep("53");
        all.add(quiz13);

        Quiz quiz14 = new Quiz();
        quiz14.setId(14);
        quiz14.setQuestion("Quel est le nombre de mort dû au Covid-19 au Congo (12/05/2020) ?");
        quiz14.setRep1("06");
        quiz14.setRep2("11");
        quiz14.setRep3("09");
        quiz14.setBoeRep("11");
        all.add(quiz14);

        return all;
    }
}
