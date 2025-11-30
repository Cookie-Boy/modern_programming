package ru.sibsutis.modern.lab9;

import java.util.*;
import java.util.stream.Collectors;

public class TPoly {
    private List<TMember> members;

    public TPoly() {
        this.members = new ArrayList<>();
        this.members.add(new TMember(0, 0));
    }

    public TPoly(int coefficient, int degree) {
        this.members = new ArrayList<>();
        if (coefficient != 0) {
            this.members.add(new TMember(coefficient, degree));
        } else {
            this.members.add(new TMember(0, 0));
        }
        normalize();
    }

    public TPoly(List<TMember> members) {
        this.members = new ArrayList<>();
        for (TMember member : members) {
            this.members.add(new TMember(member));
        }
        normalize();
    }

    public TPoly(TPoly other) {
        this.members = new ArrayList<>();
        for (TMember member : other.members) {
            this.members.add(new TMember(member));
        }
        normalize();
    }

    public int getDegree() {
        if (members.isEmpty() || (members.size() == 1 && members.getFirst().isZero())) {
            return 0;
        }

        int maxDegree = 0;
        for (TMember member : members) {
            if (!member.isZero() && member.getDegree() > maxDegree) {
                maxDegree = member.getDegree();
            }
        }
        return maxDegree;
    }

    public int getCoefficient(int degree) {
        for (TMember member : members) {
            if (member.getDegree() == degree && !member.isZero()) {
                return member.getCoefficient();
            }
        }
        return 0;
    }

    public void clear() {
        this.members.clear();
        this.members.add(new TMember(0, 0));
    }

    public TPoly add(TPoly other) {
        List<TMember> resultMembers = new ArrayList<>();

        for (TMember member : this.members) {
            resultMembers.add(new TMember(member));
        }

        for (TMember otherMember : other.members) {
            if (!otherMember.isZero()) {
                boolean found = false;
                for (TMember resultMember : resultMembers) {
                    if (resultMember.getDegree() == otherMember.getDegree()) {
                        resultMember.setCoefficient(
                                resultMember.getCoefficient() + otherMember.getCoefficient()
                        );
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    resultMembers.add(new TMember(otherMember));
                }
            }
        }

        return new TPoly(resultMembers);
    }

    public TPoly multiply(TPoly other) {
        if (this.isZero() || other.isZero()) {
            return new TPoly();
        }

        Map<Integer, Integer> coefficientMap = new HashMap<>();

        for (TMember member1 : this.members) {
            if (member1.isZero()) continue;

            for (TMember member2 : other.members) {
                if (member2.isZero()) continue;

                int newDegree = member1.getDegree() + member2.getDegree();
                int newCoefficient = member1.getCoefficient() * member2.getCoefficient();

                coefficientMap.merge(newDegree, newCoefficient, Integer::sum);
            }
        }

        List<TMember> resultMembers = coefficientMap.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .map(entry -> new TMember(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());

        if (resultMembers.isEmpty()) {
            resultMembers.add(new TMember(0, 0));
        }

        return new TPoly(resultMembers);
    }

    public TPoly subtract(TPoly other) {
        return this.add(other.negate());
    }

    public TPoly negate() {
        List<TMember> resultMembers = new ArrayList<>();
        for (TMember member : this.members) {
            resultMembers.add(new TMember(-member.getCoefficient(), member.getDegree()));
        }
        return new TPoly(resultMembers);
    }

    public boolean equals(TPoly other) {
        if (this == other) return true;
        if (other == null) return false;

        TPoly normalizedThis = new TPoly(this);
        TPoly normalizedOther = new TPoly(other);

        if (normalizedThis.members.size() != normalizedOther.members.size()) {
            return false;
        }

        for (TMember member : normalizedThis.members) {
            if (!member.isZero()) {
                boolean found = false;
                for (TMember otherMember : normalizedOther.members) {
                    if (member.equals(otherMember)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        return true;
    }

    public TPoly differentiate() {
        List<TMember> resultMembers = new ArrayList<>();

        for (TMember member : this.members) {
            if (!member.isZero()) {
                TMember derivative = member.differentiate();
                if (!derivative.isZero()) {
                    resultMembers.add(derivative);
                }
            }
        }

        if (resultMembers.isEmpty()) {
            resultMembers.add(new TMember(0, 0));
        }

        return new TPoly(resultMembers);
    }

    public double evaluate(double x) {
        double result = 0.0;
        for (TMember member : this.members) {
            result += member.evaluate(x);
        }
        return result;
    }

    public TMember getMember(int index) {
        if (index < 0 || index >= members.size()) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        return new TMember(members.get(index));
    }

    public int getMemberCount() {
        return (int) members.stream().filter(member -> !member.isZero()).count();
    }

    public boolean isZero() {
        return members.stream().allMatch(TMember::isZero) ||
                (members.size() == 1 && members.get(0).isZero());
    }

    private void normalize() {
        members.removeIf(member -> member.isZero() && member.getDegree() != 0);
        if (members.isEmpty()) {
            members.add(new TMember(0, 0));
            return;
        }

        Map<Integer, Integer> coefficientMap = new HashMap<>();
        for (TMember member : members) {
            if (!member.isZero()) {
                coefficientMap.merge(member.getDegree(), member.getCoefficient(), Integer::sum);
            }
        }

        members = coefficientMap.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .map(entry -> new TMember(entry.getValue(), entry.getKey()))
                .collect(Collectors.toList());

        members.sort((m1, m2) -> Integer.compare(m2.getDegree(), m1.getDegree()));

        if (members.isEmpty()) {
            members.add(new TMember(0, 0));
        }
    }

    @Override
    public String toString() {
        if (isZero()) {
            return "0";
        }

        List<String> terms = new ArrayList<>();
        for (TMember member : members) {
            if (!member.isZero()) {
                terms.add(member.toString());
            }
        }

        if (terms.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < terms.size(); i++) {
            String term = terms.get(i);
            if (i == 0) {
                sb.append(term);
            } else {
                if (term.startsWith("-")) {
                    sb.append(" - ").append(term.substring(1));
                } else {
                    sb.append(" + ").append(term);
                }
            }
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TPoly tPoly = (TPoly) obj;
        return this.equals(tPoly);
    }

    @Override
    public int hashCode() {
        return Objects.hash(members);
    }
}
