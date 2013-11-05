package gamesys.grailsexercise

class Employee {

    String fullName
    Date startDate
    Integer holidayAllowance

    static constraints = {
        fullName(unique: true, blank: false, nullable: false)
        startDate(blank: false, nullable: false, max: new Date())
        holidayAllowance(blank: false, nullable: false, max: 30,
                validator: { val, obj ->
                    def start = new Date()
                    def end = obj.startDate
                    def monthBetween = (start[Calendar.MONTH] - end[Calendar.MONTH]) + 1
                    def yearsBetween = start[Calendar.YEAR] - end[Calendar.YEAR]
                    def months = monthBetween + (yearsBetween * 12)
                    if (months < 12){
                        val in 20..25
                    }
                }
        )
    }
}
