package cg.mokano.bzv.covid.services.datas;

import java.util.ArrayList;

import cg.mokano.bzv.covid.R;
import cg.mokano.bzv.covid.models.AutoDiagnostic;

public class BDAutoDiagnostic {
    public static ArrayList<AutoDiagnostic> db() {
        ArrayList<AutoDiagnostic> all = new ArrayList<>();

        AutoDiagnostic diagnostic1 = new AutoDiagnostic();
        diagnostic1.setId(1);
        diagnostic1.setQst("Ces derniers jours, avez-vous une toux ou une augmentation de votre toux habituelle ?");
        diagnostic1.setImage(R.drawable.ic_auto_toux);
        diagnostic1.setProposition1("Oui");
        diagnostic1.setProposition2("Non");
        diagnostic1.setExplication1("La toux\n\n" + "Une augmentation de toux veut dire que vous toussez plus que d'habitude");
        diagnostic1.setExplication2("");
        all.add(diagnostic1);

        AutoDiagnostic diagnostic2 = new AutoDiagnostic();
        diagnostic2.setId(2);
        diagnostic2.setQst("Ces derniers jours, avez-vous noté une forte diminution ou perte de votre goût ou votre odorat ?");
        diagnostic2.setImage(R.drawable.ic_auto_perte_gout);
        diagnostic2.setProposition1("Oui");
        diagnostic2.setProposition2("Non");
        diagnostic2.setExplication1("La perte du gout,\n\n C'est quand vous ne pouvez plus reconnaitre le goût d'un aliment.\n Par exemple, le poulet a le même goût que le posson.");
        diagnostic2.setExplication2("La perte de l'odorat,\n\n C'est quand vous ne sentez plus les odeurs, par exemple, vous ne pouvez plus sentir l'odeur du poulet qui sort du four.");
        all.add(diagnostic2);

        AutoDiagnostic diagnostic3 = new AutoDiagnostic();
        diagnostic3.setId(3);
        diagnostic3.setQst("Ces derniers jours, avez-vous eu mal de gorge et/ou des douleurs musculaires et/ou des courbatures inhabituelles ?");
        diagnostic3.setImage(R.drawable.ic_auto_mal_gorge);
        diagnostic3.setProposition1("Oui");
        diagnostic3.setProposition2("Non");
        diagnostic3.setExplication1("Un mal de gorge,\n c'est quand vous ressentez un irritation ou une gêne au niveau de votre gorge");
        diagnostic3.setExplication2("La courbature, c'est une douleur musculaire. On peut ressentir des courbature quand on afait trop de sport et quand on est malade. les muscles du corps sont fatigués, donc ils font mal.");
        all.add(diagnostic3);

        AutoDiagnostic diagnostic4 = new AutoDiagnostic();
        diagnostic4.setId(4);
        diagnostic4.setQst("Ces dernières 24 heures, avez-vous de la diarrhée ?\n Avec au moins 3 selles molles.");
        diagnostic4.setImage(R.drawable.ic_auto_diarree);
        diagnostic4.setProposition1("Oui");
        diagnostic4.setProposition2("Non");
        diagnostic4.setExplication1("La diarhée, c'est aller aux toilettes plus souvent que d'habitude.\n Il faut être allé aux toilettes au moins 3 fois dans la journée.\n La circonstance de votre caca est molle ou liquide. La quantité est plus importante");
        diagnostic4.setExplication2("");
        all.add(diagnostic4);

        AutoDiagnostic diagnostic5 = new AutoDiagnostic();
        diagnostic5.setId(5);
        diagnostic5.setQst("Ces derniers jours, avez-vous une fatigue inhabituelle ?");
        diagnostic5.setImage(R.drawable.ic_auto_fatigue);
        diagnostic5.setProposition1("Oui");
        diagnostic5.setProposition2("Non");
        diagnostic5.setExplication1("La fatigue inhabituelle,\n c'est une forme de fatigue que vous n'avez pas l'habitude de ressentir.\n Votre corps est plus faible. Les douleurs liées à cette fatigue sont plus importantes");
        diagnostic5.setExplication2("");
        all.add(diagnostic5);

        AutoDiagnostic diagnostic6 = new AutoDiagnostic();
        diagnostic6.setId(6);
        diagnostic6.setQst("Depuis 24 heures ou plus, êtes-vous dans l'impossibilité de vous alimenter ou de boire ?");
        diagnostic6.setImage(R.drawable.ic_auto_perte_appetit);
        diagnostic6.setProposition1("Oui");
        diagnostic6.setProposition2("Non");
        diagnostic6.setExplication1("Être dans l'impossibilité de manger ou de boire, c'est que vous n'arrivez plus à avaler ni nourriture ni boisson.\n Vous ressentez du dégoût quand voyez de la nourriture.");
        diagnostic6.setExplication2("");
        all.add(diagnostic6);

        AutoDiagnostic diagnostic7 = new AutoDiagnostic();
        diagnostic7.setId(7);
        diagnostic7.setQst("Ces dernières 24 heures, avez-vous noté un manque de souffle inhabituel lorsque vous parlez ou faites un petit effort ?");
        diagnostic7.setImage(R.drawable.ic_auto_essouflement);
        diagnostic7.setProposition1("Oui");
        diagnostic7.setProposition2("Non");
        diagnostic7.setExplication1("Un manque de souffle est inhabituel, quand vous avez plus de mal à respirer que les autres jours.\n Par exemple vous êtes plus essouflé quand vous parlez ou quand vous montez l'escalier");
        diagnostic7.setExplication2("");
        all.add(diagnostic7);

        AutoDiagnostic diagnostic8 = new AutoDiagnostic();
        diagnostic8.setId(8);
        diagnostic8.setQst("Avez-vous de l'hypertension arterielle mal équilibrée ?\n Ou avez-vous  une maladie cardiaque ou vasculaire ?\n Ou prenez-vous un traitement à visée cardiologique ?");
        diagnostic8.setImage(R.drawable.ic_auto_hypertension);
        diagnostic8.setProposition1("Oui");
        diagnostic8.setProposition2("Non");
        diagnostic8.setExplication1("Une Hypertension artérielle mal équilibrée, c'est une augmentation trop importante de la tension arterielle.\n La circulation du sang est trop rapide, donc le sang exerce une trop forte pression sur les artères.");
        diagnostic8.setExplication2("L'augmentation de la tension arterielle peut entraîner des maladies cardiaques ou vasculaires, c'est à dire des maladie du coeur.\n Ces maladie sont liées à un mauvais fonctionnement du coeur ou des vaisseaux sanguins qui alimentent le coeur.\n Le coeur est un muscle, ce muscle devient faible.\n\n Cette maladie necessite un traitement qu'on appelle traitement cardiologique.");
        all.add(diagnostic8);

        AutoDiagnostic diagnostic9 = new AutoDiagnostic();
        diagnostic9.setId(9);
        diagnostic9.setQst("Êtes-vous diabétique ?");
        diagnostic9.setImage(R.drawable.ic_auto_diabetique);
        diagnostic9.setProposition1("Oui");
        diagnostic9.setProposition2("Non");
        diagnostic9.setExplication1("Être diabétique c'est avoir trop de sucre dans le sang.\n Quand vous aves du diabête, c'est que votre corps ne sait plus réguler le sucre dans le sang. Il a des difficultés à utiliser le sucre consommé. Ce n'est pas parce que vous avez mangé trop de sucre.\n\n Le sucre est nécessaire au fonctionnement de votre corps. Si le corps n'utulise pas bien le sucre, il reste dans le sang.\n\n Le diabête abime les autres organes et on peut avoir des maladies.");
        diagnostic9.setExplication2("");
        all.add(diagnostic9);

        AutoDiagnostic diagnostic10 = new AutoDiagnostic();
        diagnostic10.setId(10);
        diagnostic10.setQst("Avez-vous ou avez-vous eu un cancer ces trois dernières années ?");
        diagnostic10.setImage(R.drawable.ic_auto_cancer);
        diagnostic10.setProposition1("Oui");
        diagnostic10.setProposition2("Non");
        diagnostic10.setExplication1("Le Cancer, c'est une maladie liée à un trop grande et trop rapide multiplication des cellules dans le corps.\n Elles sont donc trop nombreuses et inutules au fonctionnement du corps. Elles sont cancereuse.\n\n Ces cellules cancéreuses peuvent toucher differentes parties du corps, par exemple l'estomac, les poumons, les seins, la peau, les os,...");
        diagnostic10.setExplication2("");
        all.add(diagnostic10);

        AutoDiagnostic diagnostic11 = new AutoDiagnostic();
        diagnostic11.setId(11);
        diagnostic11.setQst("Avez-vous une maladie respiratoire ?\n Ou êtes-vous suivi par un pneumologue ?");
        diagnostic11.setImage(R.drawable.ic_auto_maladie_respiratoire);
        diagnostic11.setProposition1("Oui");
        diagnostic11.setProposition2("Non");
        diagnostic11.setExplication1("Une maladie respiratoire, c'est une maladie qui empêche de bien respirer.\n Cette maladie touche les organes qui sont nécessaires pour la respiration comme les poumons.\n Par exemple on est vite essouflé quand on fait du sport et on pousse.\n\n Un pneumologue, c'est le médecin des poumons, et des maladies liées aux poumons. Quand on a une maladie respiratoire, on va voir le pneumologue.");
        diagnostic11.setExplication2("");
        all.add(diagnostic11);

        AutoDiagnostic diagnostic12 = new AutoDiagnostic();
        diagnostic12.setId(12);
        diagnostic12.setQst("Avez-vous une insuffisance rénale chronique dialysée ?");
        diagnostic12.setImage(R.drawable.ic_auto_chronique);
        diagnostic12.setProposition1("Oui");
        diagnostic12.setProposition2("Non");
        diagnostic12.setExplication1("Une insuffisance rénale chronique, c'est une maladie des reins. Les reins ne fonctionnent plus correctement, Ils filtrent mal le sang. Les reins n'éliminent plus les déchets qui sont dans le sang. Les déchets sont de plus en plus nombreux dans le sang, et le corps s'empoisonne.\n\n ça nécessite un traitement par une dialyse.\n Une dialise est une machine qui peut filtrer le sang à la place des reins de la personne.");
        diagnostic12.setExplication2("");
        all.add(diagnostic12);

        AutoDiagnostic diagnostic13 = new AutoDiagnostic();
        diagnostic13.setId(13);
        diagnostic13.setQst("Avez vous une maladie chronique du foie ?");
        diagnostic13.setImage(R.drawable.ic_auto_foie);
        diagnostic13.setProposition1("Oui");
        diagnostic13.setProposition2("Non");
        diagnostic13.setExplication1("La maladie chronique du foie, c'est lorsque notre foie ou une partie de notre foie ne fonctionne plus.\n\n Le foie nettoie le sang et donc le corps, et fabrique ce qui est essentiel pour la digestion et pour la bonne santé des muscles, de la peau, des os.\n Par exemple quand on boit beaucoup d'alcool ça peut abimer notre foie.");
        diagnostic13.setExplication2("");
        all.add(diagnostic13);

        AutoDiagnostic diagnostic14 = new AutoDiagnostic();
        diagnostic14.setId(14);
        diagnostic14.setQst("Avez-vous une maladie connue pour diminuer vos defenses immunitaires ?");
        diagnostic14.setImage(R.drawable.ic_auto_defence_immunitaire);
        diagnostic14.setProposition1("Oui");
        diagnostic14.setProposition2("Non");
        diagnostic14.setExplication1("Les défenses immunitaires, ce sont les défenses du corps. Le corps a un système qui le protège contre toutes infections, des microbes: bactéries, virus...\n c'est le système immunitaire.\n\n Si vous avez une maladie qui diminue vos défenses immunitaires, vous avez plus de risque de tomber malade, car le corps gère déja une autre infetion, il a du mal à se protéger.");
        diagnostic14.setExplication2("");
        all.add(diagnostic14);

        AutoDiagnostic diagnostic15 = new AutoDiagnostic();
        diagnostic15.setId(15);
        diagnostic15.setQst("Prenez-vous un traitement immunosuppresseur ?\n C'est un traitement qui diminue vos défenses contre les infections.");
        diagnostic15.setImage(R.drawable.ic_auto_traitement);
        diagnostic15.setProposition1("Oui");
        diagnostic15.setProposition2("Non");
        diagnostic15.setExplication1("Un traitement immunosuppresseur, c'est un traitement qu'on donne pour certains maladies. Ce traitement diminue les défences immunitaires.\n Par exemple pour les personnes qui ont reçu une greffe d'organe.\n Ce traitement permet au corps de ne pas se defendre contre cet organe qu'il considère comme étranger.\n\n Ce traitement rend le corps faible donc il a plus de risque d'attraper un virus.");
        diagnostic15.setExplication2("");
        all.add(diagnostic15);

        AutoDiagnostic diagnostic16 = new AutoDiagnostic();
        diagnostic16.setId(16);
        diagnostic16.setQst("Votre température est-elle supérieure à 38°C ?");
        diagnostic16.setImage(R.drawable.ic_auto_fievre);
        diagnostic16.setProposition1("Oui");
        diagnostic16.setProposition2("Non");
        diagnostic16.setExplication1("Une fièvre au-delà de 38°C est considérée comme un risque de santé, il faut consulté un médecin.");
        diagnostic16.setExplication2("");
        all.add(diagnostic16);

        AutoDiagnostic diagnostic17 = new AutoDiagnostic();
        diagnostic17.setId(17);
        diagnostic17.setQst("Avez-vous voyagé ou séjourné dans l’un de ces pays touchés par la COVID-19 ?");
        diagnostic17.setImage(R.drawable.ic_auto_avion);
        diagnostic17.setProposition1("Oui");
        diagnostic17.setProposition2("Non");
        diagnostic17.setExplication1("Un voyage est un déplacement dans l'espace, contraint, éffectué vers un point plus ou moins éloigné dans un but personnel ou professionnel ou autre.");
        diagnostic17.setExplication2("");
        all.add(diagnostic17);

        AutoDiagnostic diagnostic18 = new AutoDiagnostic();
        diagnostic18.setId(18);
        diagnostic18.setQst("Un membre de votre foyer est atteint du COVID-19 ?");
        diagnostic18.setImage(R.drawable.ic_auto_famille_malade);
        diagnostic18.setProposition1("Oui");
        diagnostic18.setProposition2("Non");
        diagnostic18.setExplication1("");
        diagnostic18.setExplication2("");
        all.add(diagnostic18);

        AutoDiagnostic diagnostic19 = new AutoDiagnostic();
        diagnostic19.setId(19);
        diagnostic19.setQst("Avez-vous eu un contact direct avec un cas confirmé, en face à face, à moins d’un mètre ? ");
        diagnostic19.setImage(R.drawable.ic_auto_contact_malade);
        diagnostic19.setProposition1("Oui");
        diagnostic19.setProposition2("Non");
        diagnostic19.setExplication1("");
        diagnostic19.setExplication2("");
        all.add(diagnostic19);

        AutoDiagnostic diagnostic20 = new AutoDiagnostic();
        diagnostic20.setId(20);
        diagnostic20.setQst("Êtes-vous un personnel de laboratoire manipulant des prélèvements biologiques des cas confirmés ?");
        diagnostic20.setImage(R.drawable.ic_auto_labo);
        diagnostic20.setProposition1("Oui");
        diagnostic20.setProposition2("Non");
        diagnostic20.setExplication1("");
        diagnostic20.setExplication2("");
        all.add(diagnostic20);

        return all;
    }
}
