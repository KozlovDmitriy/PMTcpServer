package pm.web.cbr;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;

/**
 * Класс части решения прецедента (case solution).
 * @author Mikhail Navrotskiy
 * @version 1.0
 */
public class Solution implements CaseComponent {

    /* Поля класса. */
    /** Главный концепт. */
    private Instance mainConcept;
    /** Множество проблем. */
    private Instance problems;
    /** Множество рекомендаций. */
    private Instance solutions;

    /**
     * Метод получения значения главного концепта.
     * @return Значение главного концепта.
     */
    public Instance getMainConcept() {
        return mainConcept;
    }

    /**
     * Метод изменения значения главного концепта.
     * @param mainConcept Новое значение главного концепта.
     */
    public void setMainConcept(Instance mainConcept) {
        this.mainConcept = mainConcept;
    }

    /**
     * Метод получения значения проблем.
     * @return Значение проблем.
     */
    public Instance getProblems() {
        return problems;
    }

    /**
     * Метод изменения значения проблем.
     * @param problems Новое значение проблем.
     */
    public void setProblems(Instance problems) {
        this.problems = problems;
    }

    /**
     * Метод получения значения рекомендаций.
     * @return Значение рекомендациий.
     */
    public Instance getSolutions() {
        return solutions;
    }

    /**
     * Метод изменения значения рекомендаций.
     * @param solutions Новое значение рекомендаций.
     */
    public void setSolutions(Instance solutions) {
        this.solutions = solutions;
    }

    @Override
    public Attribute getIdAttribute() {
        return null;
    }
}
