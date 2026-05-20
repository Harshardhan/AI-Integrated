package practice;

class StudentKey {
    private int id;

    public StudentKey(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof StudentKey)) return false;
        StudentKey other = (StudentKey) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Key-" + id;
    }
}
