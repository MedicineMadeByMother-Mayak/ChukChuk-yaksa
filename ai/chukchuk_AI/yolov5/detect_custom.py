# YOLOv5 🚀 by Ultralytics, AGPL-3.0 license
"""
Run YOLOv5 detection inference on images, videos, directories, globs, YouTube, webcam, streams, etc.

Usage - sources:
    $ python detect.py --weights yolov5s.pt --source 0                               # webcam
                                                     img.jpg                         # image
                                                     vid.mp4                         # video
                                                     screen                          # screenshot
                                                     path/                           # directory
                                                     list.txt                        # list of images
                                                     list.streams                    # list of streams
                                                     'path/*.jpg'                    # glob
                                                     'https://youtu.be/LNwODJXcvt4'  # YouTube
                                                     'rtsp://example.com/media.mp4'  # RTSP, RTMP, HTTP stream

Usage - formats:
    $ python detect.py --weights yolov5s.pt                 # PyTorch
                                 yolov5s.torchscript        # TorchScript
                                 yolov5s.onnx               # ONNX Runtime or OpenCV DNN with --dnn
                                 yolov5s_openvino_model     # OpenVINO
                                 yolov5s.engine             # TensorRT
                                 yolov5s.mlmodel            # CoreML (macOS-only)
                                 yolov5s_saved_model        # TensorFlow SavedModel
                                 yolov5s.pb                 # TensorFlow GraphDef
                                 yolov5s.tflite             # TensorFlow Lite
                                 yolov5s_edgetpu.tflite     # TensorFlow Edge TPU
                                 yolov5s_paddle_model       # PaddlePaddle
"""

import argparse
import csv
import os
import platform
import sys
from pathlib import Path
import uuid

import torch

FILE = Path(__file__).resolve()
ROOT = FILE.parents[0]  # YOLOv5 root directory
if str(ROOT) not in sys.path:
    sys.path.append(str(ROOT))  # add ROOT to PATH
ROOT = Path(os.path.relpath(ROOT, Path.cwd()))  # relative

mapping_dic = {'201007672': '졸로푸트정 100mg', '200810688': '놀텍정 10mg', '199400486': '에어탈정(아세클로페낙)', '200500254': '동아가바펜틴정 800mg', '201403839': '글리틴정(콜린알포세레이트)', '199500420': '비유피-4정 20mg', '200410326': '크레스토정 20mg', '200410085': '리피토정 20mg', '200611524': '마도파정', '200510974': '란스톤엘에프디티정 30mg', '201403837': '브린텔릭스정 20mg', '200700907': '플라빅스정 75mg', '199504272': '프로스카정', '200300416': '다보타민큐정 10mg/병', '199603003': '타이레놀이알서방정(아세트아미노펜)(수출용)', '200902214': '큐시드정 31.5mg/PTP', '199500435': '엘도스캡슐(에르도스테인)(수출용)', '200209622': '자이프렉사정 2.5mg', '200607849': '스토가정 10mg', '200500251': '가바토파정 100mg', '199702182': '조인스정 200mg', '201206335': '렉사프로정 15mg', '199303108': '타이레놀정500mg', '199501099': '삼남건조수산화알루미늄겔정', '200301252': '엘스테인캡슐(에르도스테인)', '199700756': '삐콤씨에프정 618.6mg/병', '201802815': '케이캡정 50mg', '200009026': '넥시움정 40mg', '200202889': '아빌리파이정 10mg', '200708376': '라비에트정 20mg', '199703153': '씨즈날정(세티리진염산염)', '199704687': '이튼큐정 35mg/PTP', '200611395': '라시도필캡슐(비매품)', '202009337': '디카테오엘씨정 10mg/병', '200009023': '쎄로켈정 100mg', '201503658': '로수젯정10/5밀리그램', '200600026': '종근당글리아티린연질캡슐(콜린알포세레이트)\xa0', '200301818': '써스펜8시간이알서방정 650mg', '201309347': '아질렉트정(라사길린메실산염)', '200705992': '아토르바정 10mg', '200500497': '오마코연질캡슐(오메가-3-산에틸에스테르90)', '201500365': '아토젯정 10/40mg', '201310719': '글리아타민연질캡슐', '201205253': '리바로정 4mg', '200605445': '콜리네이트연질캡슐 400mg', '200607734': '트루비타정 60mg/병', '201507696': '알바스테인캡슐(에르도스테인)', '200709390': '리피로우정 20mg', '201702674': '카발린캡슐 25mg', '201804394': '엘스테인정(에르도스테인)', '200802213': '비타비백정 100mg/병', '200710817': '맥시부펜이알정 300mg', '200511058': '리리카캡슐 150mg', '201600318': '로수바미브정 10/20mg', '201306134': '비오플250캡슐 282.5mg', '200802864': '디카맥스디플러스정', '201402463': '에스원엠프정 20mg', '200802023': '쿠에타핀정 25mg', '201000406': '원더칼-디츄어블정', '200712481': '메가파워정 90mg/병', '200308324': '에빅사정(메만틴염산염)(비매품)', '200900833': '마이칼디정', '200202194': '리렉스펜정 300mg/PTP', '201602679': '뮤코원캡슐(에르도스테인)', '201205447': '디카테오정', '200300913': '이가탄에프캡슐 150mg/PTP', '201700802': '람노스캡슐500mg/병', '200703215': '인사돌플러스정 35mg/PTP', '201800568': '바이오탑디캡슐 50mg/병', '200100780': '동화덴스톨캡슐 150mg/PTP', '200811061': '액티프롤정 60mg/PTP', '201401963': '신바로정', '201803000': '바이오탑하이캡슐 150mg/병', '200905692': '클라리틴정(로라타딘)', '200704590': '펠루비정(펠루비프로펜)', '201501483': '제라타딘정(로라타딘)', '201308928': '낙소졸정 500/20mg', '200610765': '지르텍정(세티리진염산염)', '201006967': '울트라셋이알서방정', '201201587': '레일라정', '200300092': '투스딘골드캡슐 150mg/PTP', '201602714': '알러비정(세티리진염산염)', '201200417': '비모보정 500/20mg', '201602610': '락토엔큐캡슐(바실루스리케니포르미스균)\xa0', '200710758': '자누비아정 50mg', '201005081': '트윈스타정 40/5mg', '200403500': '엔테론정 150mg', '201405223': '제미메트서방정 50/1000mg', '200410088': '카듀엣정 5mg/20mg', '201004570': '세비카정 10/40mg', '201401986': '트루패스정 4mg', '200901817': '자누메트정 50/850mg', '201005501': '카나브정 60mg', '201106367': '트라젠타정(리나글립틴)', '201309418': '자누메트엑스알서방정 100/1000mg', '201208291': '트라젠타듀오정 2.5/850mg', '200902349': '아모잘탄정 5/100mg', '200703803': '엑스포지정 5/160mg', '200409982': '아보다트연질캡슐 0.5mg', '200701034': '자트랄엑스엘정 10mg', '200610660': '노바스크정 5mg', '200703673': '베시케어정 10mg', '201602113': '엔트레스토필름코팅정 200mg', '201102769': '플리바스정 50mg', '200810113': '토비애즈서방정 4mg', '200108429': '아스피린프로텍트정 100mg', '199201106': '알드린정', '199200261': '동아오팔몬정(리마프로스트알파-시클로덱스트린포접화합물)', '199102825': '무코스타정(레바미피드)(비매품)', '199201807': '뉴로메드정(옥시라세탐)', '200402674': '한미탐스캡슐 0.2mg', '199101125': '일양하이트린정 2mg', '199102014': '기넥신에프정(은행엽엑스)(수출용)', '198700706': '보령부스파정 5mg', '198801531': '뮤테란캡슐 100mg', '197900277': '게보린정 300mg/PTP', '197400246': '마그밀정(수산화마그네슘)', '198700430': '알마겔정(알마게이트)(수출명:유한가스트라겔정)'}

from ultralytics.utils.plotting import Annotator, colors, save_one_box

from models.common import DetectMultiBackend
from utils.dataloaders import IMG_FORMATS, VID_FORMATS, LoadImages, LoadScreenshots, LoadStreams
from utils.general import (
    LOGGER,
    Profile,
    check_file,
    check_img_size,
    check_imshow,
    check_requirements,
    colorstr,
    cv2,
    increment_path,
    non_max_suppression,
    print_args,
    scale_boxes,
    strip_optimizer,
    xyxy2xywh,
)
from utils.torch_utils import select_device, smart_inference_mode

class Info:
    def __init__(self, cls, name, acc, save_dir, crop_img):
        self.name = name
        self.cls = cls
        self.acc = acc
        self.save_dir = save_dir
        self.crop_img = crop_img

@smart_inference_mode()
def run(
    weights=ROOT / "runs/train/yolov5m_result2/weights/best.pt",  # model path or triton URL
    source=ROOT / "data/images",  # file/dir/URL/glob/screen/0(webcam)
    data=ROOT / "data/coco128.yaml",  # dataset.yaml path
    imgsz=(640, 640),  # inference size (height, width)
    conf_thres=0.25,  # confidence threshold
    iou_thres=0.45,  # NMS IOU threshold
    max_det=1000,  # maximum detections per image
    device="",  # cuda device, i.e. 0 or 0,1,2,3 or cpu
    view_img=False,  # show results
    save_txt=False,  # save results to *.txt
    save_csv=False,  # save results in CSV format
    save_crop=False,  # save cropped prediction boxes
    save_conf=False,
    nosave=False,  # do not save images/videos
    classes=None,  # filter by class: --class 0, or --class 0 2 3
    agnostic_nms=False,  # class-agnostic NMS
    augment=False,  # augmented inference
    visualize=False,  # visualize features
    update=False,  # update all models
    project=ROOT / "runs/detect",  # save results to project/name
    name="exp",  # save results to project/name
    exist_ok=False,  # existing project/name ok, do not increment
    line_thickness=3,  # bounding box thickness (pixels)
    hide_labels=False,  # hide labels
    hide_conf=False,  # hide confidences
    half=False,  # use FP16 half-precision inference
    dnn=False,  # use OpenCV DNN for ONNX inference
    vid_stride=1,  # video frame-rate stride
    return_info=True):
    source = str(source)
    save_img = not nosave and not source.endswith(".txt")  # save inference images
    is_file = Path(source).suffix[1:] in (IMG_FORMATS + VID_FORMATS)
    is_url = source.lower().startswith(("rtsp://", "rtmp://", "http://", "https://"))
    webcam = source.isnumeric() or source.endswith(".streams") or (is_url and not is_file)
    screenshot = source.lower().startswith("screen")
    result = []

    if is_url and is_file:
        source = check_file(source)  # download

    # Directories
    save_dir = increment_path(Path(project) / name, exist_ok=exist_ok)  # increment run
    (save_dir / "labels" if save_txt else save_dir).mkdir(parents=True, exist_ok=True)  # make dir

    # Load model
    device = select_device(device)
    model = DetectMultiBackend(weights, device=device, dnn=dnn, data=data, fp16=half)
    stride, names, pt = model.stride, model.names, model.pt
    imgsz = check_img_size(imgsz, s=stride)  # check image size

    # Dataloader
    bs = 1  # batch_size
    if webcam:
        view_img = check_imshow(warn=True)
        dataset = LoadStreams(source, img_size=imgsz, stride=stride, auto=pt, vid_stride=vid_stride)
        bs = len(dataset)
    elif screenshot:
        dataset = LoadScreenshots(source, img_size=imgsz, stride=stride, auto=pt)
    else:
        dataset = LoadImages(source, img_size=imgsz, stride=stride, auto=pt, vid_stride=vid_stride)
    vid_path, vid_writer = [None] * bs, [None] * bs

    # Run inference
    model.warmup(imgsz=(1 if pt or model.triton else bs, 3, *imgsz))  # warmup
    seen, windows, dt = 0, [], (Profile(device=device), Profile(device=device), Profile(device=device))
    for path, im, im0s, vid_cap, s in dataset:
        with dt[0]:
            im = torch.from_numpy(im).to(model.device)
            im = im.half() if model.fp16 else im.float()  # uint8 to fp16/32
            im /= 255  # 0 - 255 to 0.0 - 1.0
            if len(im.shape) == 3:
                im = im[None]  # expand for batch dim
            if model.xml and im.shape[0] > 1:
                ims = torch.chunk(im, im.shape[0], 0)

        # Inference
        with dt[1]:
            visualize = increment_path(save_dir / Path(path).stem, mkdir=True) if visualize else False
            if model.xml and im.shape[0] > 1:
                pred = None
                for image in ims:
                    if pred is None:
                        pred = model(image, augment=augment, visualize=visualize).unsqueeze(0)
                    else:
                        pred = torch.cat((pred, model(image, augment=augment, visualize=visualize).unsqueeze(0)), dim=0)
                pred = [pred, None]
            else:
                pred = model(im, augment=augment, visualize=visualize)
        # NMS
        with dt[2]:
            pred = non_max_suppression(pred, conf_thres, iou_thres, classes, agnostic_nms, max_det=max_det)

        # Second-stage classifier (optional)
        # pred = utils.general.apply_classifier(pred, classifier_model, im, im0s)

        # Define the path for the CSV file
        csv_path = save_dir / "predictions.csv"

        # Create or append to the CSV file
        def write_to_csv(image_name, prediction, confidence):
            """Writes prediction data for an image to a CSV file, appending if the file exists."""
            data = {"Image Name": image_name, "Prediction": prediction, "Confidence": confidence}
            with open(csv_path, mode="a", newline="") as f:
                writer = csv.DictWriter(f, fieldnames=data.keys())
                if not csv_path.is_file():
                    writer.writeheader()
                writer.writerow(data)

        # Process predictions
        for i, det in enumerate(pred):  # per image
            seen += 1
            if webcam:  # batch_size >= 1
                p, im0, frame = path[i], im0s[i].copy(), dataset.count
                s += f"{i}: "
            else:
                p, im0, frame = path, im0s.copy(), getattr(dataset, "frame", 0)

            p = Path(p)  # to Path
            save_path = str(save_dir / p.name)  # im.jpg
            txt_path = str(save_dir / "labels" / p.stem) + ("" if dataset.mode == "image" else f"_{frame}")  # im.txt
            s += "%gx%g " % im.shape[2:]  # print string
            gn = torch.tensor(im0.shape)[[1, 0, 1, 0]]  # normalization gain whwh
            imc = im0.copy() if save_crop else im0  # for save_crop
            annotator = Annotator(im0, line_width=line_thickness, example=str('가나다'))
            if len(det):
                # Rescale boxes from img_size to im0 size
                det[:, :4] = scale_boxes(im.shape[2:], det[:, :4], im0.shape).round()

                # Print results
                # for c in det[:, 5].unique():
                #     n = (det[:, 5] == c).sum()  # detections per class
                #     s += f"{n} {names[int(c)]}{'s' * (n > 1)}, "  # add to string

                # Write results
                for *xyxy, conf, cls in reversed(det):
                    c = int(cls)  # integer class
                    label = names[c] if hide_conf else f"{names[c]}"
                    confidence = float(conf)
                    confidence_str = f"{confidence:.2f}"

                    if save_csv:
                        write_to_csv(p.name, label, confidence_str)

                    if save_txt:  # Write to file
                        xywh = (xyxy2xywh(torch.tensor(xyxy).view(1, 4)) / gn).view(-1).tolist()  # normalized xywh
                        line = (cls, *xywh, (conf * 100))  # label format
                        with open(f"{txt_path}.txt", "a") as f:
                            f.write(("%g " * len(line)).rstrip() % line)
                            f.write(" " + mapping_dic[label] + "\n")

                    if return_info:  # Write to file
                        random_img_name = uuid.uuid1()
                        save_one_box(xyxy, imc, file=save_dir / "crops" / names[c] / f"{random_img_name}.jpg", BGR=True)
                        info = Info(names[c], mapping_dic[label], conf * 100, save_dir, f"{random_img_name}.jpg")
                        result.append(info)

                    if save_img or save_crop or view_img:  # Add bbox to image
                        c = int(cls)  # integer class
                        #확률은 conf * 100:.2f 로 뽑아옴
                        label = None if hide_labels else (names[c] if hide_conf else f"{mapping_dic[names[c]]}")
                        annotator.box_label(xyxy, label, color=colors(c, True))
                    if save_crop:
                        save_one_box(xyxy, imc, file=save_dir / "crops" / names[c] / f"{p.stem}.jpg", BGR=True)

            # Stream results
            im0 = annotator.result()
            if view_img:
                if platform.system() == "Linux" and p not in windows:
                    windows.append(p)
                    cv2.namedWindow(str(p), cv2.WINDOW_NORMAL | cv2.WINDOW_KEEPRATIO)  # allow window resize (Linux)
                    cv2.resizeWindow(str(p), im0.shape[1], im0.shape[0])
                cv2.imshow(str(p), im0)
                cv2.waitKey(1)  # 1 millisecond

            # Save results (image with detections)
            if save_img:
                if dataset.mode == "image":
                    cv2.imwrite(save_path, im0)
                else:  # 'video' or 'stream'
                    if vid_path[i] != save_path:  # new video
                        vid_path[i] = save_path
                        if isinstance(vid_writer[i], cv2.VideoWriter):
                            vid_writer[i].release()  # release previous video writer
                        if vid_cap:  # video
                            fps = vid_cap.get(cv2.CAP_PROP_FPS)
                            w = int(vid_cap.get(cv2.CAP_PROP_FRAME_WIDTH))
                            h = int(vid_cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
                        else:  # stream
                            fps, w, h = 30, im0.shape[1], im0.shape[0]
                        save_path = str(Path(save_path).with_suffix(".mp4"))  # force *.mp4 suffix on results videos
                        vid_writer[i] = cv2.VideoWriter(save_path, cv2.VideoWriter_fourcc(*"mp4v"), fps, (w, h))
                    vid_writer[i].write(im0)

        # Print time (inference-only)
        # LOGGER.info(f"{s}{'' if len(det) else '(no detections), '}{dt[1].dt * 1E3:.1f}ms")

    # Print results
    t = tuple(x.t / seen * 1e3 for x in dt)  # speeds per image
    # LOGGER.info(f"Speed: %.1fms pre-process, %.1fms inference, %.1fms NMS per image at shape {(1, 3, *imgsz)}" % t)
    # if save_txt or save_img:
    #     s = f"\n{len(list(save_dir.glob('labels/*.txt')))} labels saved to {save_dir / 'labels'}" if save_txt else ""
    #     LOGGER.info(f"Results saved to {colorstr('bold', save_dir)}{s}")
    if update:
        strip_optimizer(weights[0])  # update model (to fix SourceChangeWarning)

    return result


def parse_opt():
    """Parses command-line arguments for YOLOv5 detection, setting inference options and model configurations."""
    parser = argparse.ArgumentParser()
    parser.add_argument("--weights", nargs="+", type=str, default=ROOT / "yolov5s.pt", help="model path or triton URL")
    parser.add_argument("--source", type=str, default=ROOT / "data/images", help="file/dir/URL/glob/screen/0(webcam)")
    parser.add_argument("--data", type=str, default=ROOT / "data/coco128.yaml", help="(optional) dataset.yaml path")
    parser.add_argument("--imgsz", "--img", "--img-size", nargs="+", type=int, default=[640], help="inference size h,w")
    parser.add_argument("--conf-thres", type=float, default=0.25, help="confidence threshold")
    parser.add_argument("--iou-thres", type=float, default=0.45, help="NMS IoU threshold")
    parser.add_argument("--max-det", type=int, default=1000, help="maximum detections per image")
    parser.add_argument("--device", default="", help="cuda device, i.e. 0 or 0,1,2,3 or cpu")
    parser.add_argument("--view-img", action="store_true", help="show results")
    parser.add_argument("--save-txt", action="store_true", help="save results to *.txt")
    parser.add_argument("--save-csv", action="store_true", help="save results in CSV format")
    parser.add_argument("--save-conf", action="store_true", help="save confidences in --save-txt labels")
    parser.add_argument("--save-crop", action="store_true", help="save cropped prediction boxes")
    parser.add_argument("--nosave", action="store_true", help="do not save images/videos")
    parser.add_argument("--classes", nargs="+", type=int, help="filter by class: --classes 0, or --classes 0 2 3")
    parser.add_argument("--agnostic-nms", action="store_true", help="class-agnostic NMS")
    parser.add_argument("--augment", action="store_true", help="augmented inference")
    parser.add_argument("--visualize", action="store_true", help="visualize features")
    parser.add_argument("--update", action="store_true", help="update all models")
    parser.add_argument("--project", default=ROOT / "runs/detect", help="save results to project/name")
    parser.add_argument("--name", default="exp", help="save results to project/name")
    parser.add_argument("--exist-ok", action="store_true", help="existing project/name ok, do not increment")
    parser.add_argument("--line-thickness", default=3, type=int, help="bounding box thickness (pixels)")
    parser.add_argument("--hide-labels", default=False, action="store_true", help="hide labels")
    parser.add_argument("--hide-conf", default=False, action="store_true", help="hide confidences")
    parser.add_argument("--half", action="store_true", help="use FP16 half-precision inference")
    parser.add_argument("--dnn", action="store_true", help="use OpenCV DNN for ONNX inference")
    parser.add_argument("--vid-stride", type=int, default=1, help="video frame-rate stride")
    opt = parser.parse_args()
    opt.imgsz *= 2 if len(opt.imgsz) == 1 else 1  # expand
    print_args(vars(opt))
    return opt


def main(opt):
    """Executes YOLOv5 model inference with given options, checking requirements before running the model."""
    check_requirements(ROOT / "requirements.txt", exclude=("tensorboard", "thop"))
    run(**vars(opt))


if __name__ == "__main__":
    opt = parse_opt()
    main(opt)