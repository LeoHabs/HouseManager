public class IncomeSource {
    private String nameOfIncomeSource;
    private double valueofIncome;

    private boolean monthlyIncome;

    private int day;

    public IncomeSource(String nameOfIncomeSource, double valueofIncome, boolean monthlyIncome) {
        this.nameOfIncomeSource = nameOfIncomeSource;
        this.valueofIncome = valueofIncome;
        this.monthlyIncome = monthlyIncome;
    }

    public String getNameOfIncomeSource() {
        return nameOfIncomeSource;
    }

    public void setNameOfIncomeSource(String nameOfIncomeSource) {
        this.nameOfIncomeSource = nameOfIncomeSource;
    }

    public double getValueofIncome() {
        return valueofIncome;
    }

    public void setValueofIncome(double valueofIncome) {
        this.valueofIncome = valueofIncome;
    }

    public boolean isMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(boolean monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
