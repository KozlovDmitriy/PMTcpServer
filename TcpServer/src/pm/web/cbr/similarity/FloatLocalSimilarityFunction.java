package pm.web.cbr.similarity;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import jcolibri.datatypes.Instance;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.InrecaLessIsBetter;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.InrecaMoreIsBetter;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.McSherryLessIsBetter;
import jcolibri.util.OntoBridgeSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Локальная мера сходства для float чисел.
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class FloatLocalSimilarityFunction implements LocalSimilarityFunction {

    /* Поле класса. */
    /** Значение интервала. */
    private double interval;

    private double weight;

    /**
     * Конструктор с параметром.
     * @param interval Значение интервала для меры сходства.
     */
    public FloatLocalSimilarityFunction(double interval, double weight) {
        this.interval = interval;
        this.weight = weight;
    }

    /**
     * Метод сравнения прецедентов.
     * @param caseObject Прецедент из бд.
     * @param queryObject Запрос.
     * @return Результат сравнения.
     * @throws NoApplicableSimilarityFunctionException
     */
    @Override
    public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException {

        if ((caseObject == null) || (queryObject == null))
            return 0;
        if (!(caseObject instanceof Instance))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
        if (!(queryObject instanceof Float))
            throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());

        OntoBridge ob = OntoBridgeSingleton.getOntoBridge();
        Instance c = (Instance) caseObject;
        Float query = (Float) queryObject;
        String cValue = this.getProperty(ob, c, "value");
        Float value = Float.parseFloat(cValue);

        Interval interval = new Interval(this.interval);
//        float percent;
//
//        if (query > value) {
//            percent = value / query;
//        } else if (query < value) {
//            percent = query / value;
//        } else {
//            percent = 1;
//        }

//        double result = percent;
          double result = interval.compute(query, value);
//        // Эксперимент 3: InrecaLessIsBetter
//        McSherryLessIsBetter inrecaLessIsBetter = new McSherryLessIsBetter(this.interval,1);
//        double result = inrecaLessIsBetter.compute(value, query);

        return result * weight;
    }

    /**
     * Метод проверки корректности запросов.
     * @param caseObject Объект прецедента из БД.
     * @param queryObject Объект запроса.
     * @return Факт корректности параметров.
     */
    @Override
    public boolean isApplicable(Object caseObject, Object queryObject) {

        if ((caseObject == null) && (queryObject == null))
            return true;
        else if (caseObject == null)
            return queryObject instanceof Float;
        else if (queryObject == null)
            return caseObject instanceof Instance;
        else
            return (caseObject instanceof Instance) && (queryObject instanceof Float);
    }

    /**
     * Метод получения значения свойства прецедента.
     * @param ob Объект для доступа к онтологии.
     * @param ins Экземпляр класса.
     * @param name Имя свойства.
     * @return Значение свойства.
     */
    private String getProperty(OntoBridge ob, Instance ins, String name)
    {
        List<String> properties = new ArrayList<String>();
        List<String> values = new ArrayList<String>();
        String str="";

        ob.listInstancePropertiesValues(ins.toString(), properties, values);
        int i = properties.indexOf(ob.getURI(name));
        if (i>-1) str = values.get(i).substring(0,values.get(i).indexOf('^'));
        else {
            str = ins.toString().substring(1).replace('_', '.');
        }

        return str;
    }
}
