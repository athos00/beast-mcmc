package dr.app.beauti.enumTypes;

import dr.app.beauti.options.Parameter;

import java.text.NumberFormat;

/**
 * @author Alexei Drummond
 */
public enum PriorType {

    NONE,
    UNIFORM_PRIOR,
    EXPONENTIAL_PRIOR,
    LAPLACE_PRIOR,
    NORMAL_PRIOR,
    LOGNORMAL_PRIOR,
    GAMMA_PRIOR,
    INVERSE_GAMMA_PRIOR,
    JEFFREYS_PRIOR,
    TRUNC_NORMAL_PRIOR,
    POISSON_PRIOR;


    public String toString() {

        switch (this) {
            case NONE:
                return "none";
            case UNIFORM_PRIOR:
                return "Uniform";
            case EXPONENTIAL_PRIOR:
                return "Exponential";
            case LAPLACE_PRIOR:
                return "Laplace";
            case NORMAL_PRIOR:
                return "Normal";
            case LOGNORMAL_PRIOR:
                return "Lognormal";
            case GAMMA_PRIOR:
                return "Gamma";
            case INVERSE_GAMMA_PRIOR:
                return "Inverse Gamma";
            case JEFFREYS_PRIOR:
                return "1/x"; //rename Jeffreys prior to 1/x prior everywhere in Beauti
            case POISSON_PRIOR:
                return "Poisson";
            case TRUNC_NORMAL_PRIOR:
                return "Truncated Normal";
            default:
                return "";
        }
    }

    public String getPriorString(Parameter param) {

        NumberFormat formatter = NumberFormat.getNumberInstance();
        StringBuffer buffer = new StringBuffer();

        if (!param.isPriorEdited()) {
            buffer.append("* ");
        }
        switch (param.priorType) {
            case NONE:
                buffer.append("Using Tree Prior");
                break;
            case UNIFORM_PRIOR:
                if (!param.isDiscrete && !param.isStatistic) {
                    buffer.append("Uniform [");
                    buffer.append(formatter.format(param.lower));
                    buffer.append(", ");
                    buffer.append(formatter.format(param.upper));
                    buffer.append("]");
                } else {
                    buffer.append("Uniform");
                }
                break;
            case EXPONENTIAL_PRIOR:
                buffer.append("Exponential [");
                buffer.append(formatter.format(param.mean));
                buffer.append("]");
                break;
            case LAPLACE_PRIOR:
                buffer.append("Laplace [");
                buffer.append(formatter.format(param.mean));
                buffer.append("]");
                break;
            case NORMAL_PRIOR:
                buffer.append("Normal [");
                buffer.append(formatter.format(param.mean));
                buffer.append(", ");
                buffer.append(formatter.format(param.stdev));
                buffer.append("]");
                break;
            case LOGNORMAL_PRIOR:
                buffer.append("LogNormal [");
                if (param.isMeanInRealSpace()) buffer.append("R"); 
                buffer.append(formatter.format(param.mean));
                buffer.append(", ");
                buffer.append(formatter.format(param.stdev));
                buffer.append("]");
                break;
            case GAMMA_PRIOR:
                buffer.append("Gamma [");
                buffer.append(formatter.format(param.shape));
                buffer.append(", ");
                buffer.append(formatter.format(param.scale));
                buffer.append("]");
                break;
            case INVERSE_GAMMA_PRIOR:
                buffer.append("Inverse Gamma [");
                buffer.append(formatter.format(param.shape));
                buffer.append(", ");
                buffer.append(formatter.format(param.scale));
                buffer.append("]");
                break;
            case JEFFREYS_PRIOR:
                buffer.append("1/x"); // rename Jeffreys prior to 1/x prior everywhere in Beauti
                break;
            case POISSON_PRIOR:
                buffer.append("Poisson [");
                buffer.append(formatter.format(param.mean));
                buffer.append("]");
                break;
            case TRUNC_NORMAL_PRIOR:
                buffer.append("Truncated Normal [");
                buffer.append(formatter.format(param.mean));
                buffer.append(", ");
                buffer.append(formatter.format(param.stdev));
                buffer.append("]");
                buffer.append(" in [");
                buffer.append(formatter.format(param.lower));
                buffer.append(", ");
                buffer.append(formatter.format(param.upper));
                buffer.append("]");

                break;
            default:
                throw new IllegalArgumentException("Unknown prior type");
        }
        if (param.priorType != PriorType.NONE && !param.isStatistic) {
            buffer.append(", initial=").append(param.initial);
        }

        return buffer.toString();
    }
}
