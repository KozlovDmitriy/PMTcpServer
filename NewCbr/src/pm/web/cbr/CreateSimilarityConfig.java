package pm.web.cbr;

import jcolibri.cbrcore.Attribute;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import pm.web.cbr.similarity.FloatLocalSimilarityFunction;
import pm.web.cbr.similarity.IntegerLocalSimilarityFunction;
import pm.web.cbr.similarity.IntervalLocalSimilarity;

/**
 * Класс создания разных настроек для CBR с разными локальными мерами сходства.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class CreateSimilarityConfig {

    /**
     * Эксперимент 1 - использование своей меры близости.
     * @return Настройка для CBR.
     */
    public static NNConfig experiment_1() {

        NNConfig result = new NNConfig();
        Attribute attribute;
        Double weight = 1.0;

        // begin{ЭКОНОМИЧЕСКИЕ ПАРАМЕТРЫ}

        // avCheck
        attribute = new Attribute("avCheck", Description.class);
        result.addMapping(attribute, new FloatLocalSimilarityFunction(1000, 1)); // 0.9! - interval and %
        result.setWeight(attribute, 0.3);

        // itemsCount
        attribute = new Attribute("itemsCount", Description.class);
        result.addMapping(attribute, new FloatLocalSimilarityFunction(10, 1)); //0.05! - interval and %
        result.setWeight(attribute, 0.5);

        // impl
        attribute = new Attribute("impl", Description.class);
        result.addMapping(attribute, new FloatLocalSimilarityFunction(100, 1.0));
        result.setWeight(attribute, 0.8);

//        // dynamics
//        attribute = new Attribute("dynamics", Description.class);
//        result.addMapping(attribute, new Equal());
//        result.setWeight(attribute, 0.16);
//
//        // leadership
//        attribute = new Attribute("leadership", Description.class);
//        result.addMapping(attribute, new Equal());
//        result.setWeight(attribute, 0.16);

        // end

        // begin{ПЕРСОНАЛЬНЫЕ ПАРАМЕТРЫ}

//        // expierence
//        attribute = new Attribute("expierence", Description.class);
//        result.addMapping(attribute, new Equal());
//        result.setWeight(attribute, 0.1);

        // end

        return result;
    }

    /**
     * Эксперимент 2 - использование своей меры близости: уточнение весов.
     * @return Настройка для CBR.
     */
    public static NNConfig experiment_2() {

        NNConfig result = new NNConfig();
        Attribute attribute;
        Double weight = 1.0;

        // avCheck
        attribute = new Attribute("avCheck", Description.class);
        result.addMapping(attribute, new FloatLocalSimilarityFunction(5000, 1.0));
        result.setWeight(attribute, 0.1);

        // itemsCount
        attribute = new Attribute("itemsCount", Description.class);
        result.addMapping(attribute, new FloatLocalSimilarityFunction(7, 1.0));
        result.setWeight(attribute, 0.1);

//        // checksCount
//        attribute = new Attribute("checksCount", Description.class);
//        result.addMapping(attribute, new IntegerLocalSimilarityFunction());
//        result.setWeight(attribute, 0.05);

        // impl
        attribute = new Attribute("impl", Description.class);
        result.addMapping(attribute, new FloatLocalSimilarityFunction(100, 1.0));
        result.setWeight(attribute, 0.1);

//        // implPlan
//        attribute = new Attribute("implPlan", Description.class);
//        result.addMapping(attribute, new FloatLocalSimilarityFunction(100000));
//        result.setWeight(attribute, 0.05);
//
//        // sickList
//        attribute = new Attribute("sickList", Description.class);
//        result.addMapping(attribute, new Equal());
//        result.setWeight(attribute, 0.01);
//
//        // vacation
//        attribute = new Attribute("vacation", Description.class);
//        result.addMapping(attribute, new Equal());
//        result.setWeight(attribute, 0.01);

        return result;
    }

}
